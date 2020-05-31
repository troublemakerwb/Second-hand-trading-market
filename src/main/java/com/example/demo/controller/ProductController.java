package com.example.demo.controller;

import com.example.demo.controller.param.BuyReq;
import com.example.demo.controller.param.ProductReq;
import com.example.demo.dao.entity.ProductEntity;
import com.example.demo.dao.repo.ProductRepository;
import com.example.demo.domain.Buy;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.domain.ResultData;
import com.example.demo.service.BuyService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private BuyService buyService;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/addProduct")
    public String addProduct(@RequestBody ProductEntity entity){
        entity.setPutawayDate(new Date());
        return productRepository.save(entity)!=null?"成功":"失败";
    }

    @Autowired
    private UserService userService;
    @GetMapping("/products/exception")
    public Product getError() throws Exception{
        throw new Exception("My Exception");
    }

    @GetMapping("/products")
    public List<Product> getProducts(){
        return this.productService.getAll();
    }

    @PostMapping("/search")
    public List<Product> getProducts(@RequestBody ProductReq req){
        return this.productService.search(req);
    }

    @GetMapping("/sortproducts")
    @ResponseBody
    public List<Product> sortProducts(@RequestBody String sort) throws JSONException {
        JSONArray jsonArray = null;
        jsonArray = new JSONArray("["+sort+"]");
        System.out.println(jsonArray.toString());
        System.out.println(jsonArray.getJSONObject(0).get("sort"));
        Integer realsort = Integer.valueOf(jsonArray.getJSONObject(0).get("sort").toString());
        return this.productService.sortProduct(realsort);
    }

    @PostMapping("/products/form")
    public Product createProduct(ProductReq productReq){
        log.info("Product Req in form:{}",productReq);
        Product product = this.productService.createOne(productReq);
        log.info("Create Product:{}",product);
        return product;
    }

    @PostMapping("/products/json")
    public Product createProductJson(@RequestBody ProductReq productReq){
        log.info("Product Req in Json:{}",productReq);
        Product product  = new Product();
        BeanUtils.copyProperties(productReq,product);
        log.info("Create Product:{}",product);
        product.setId(1000L);
        return product;

    }

    @Transactional
    @PutMapping("/products/{id}")
    public void updateUser(@PathVariable Long id, String productname){
        log.info("Product Update:{}, productname:{}", id, productname);
        this.productService.updateProduct(productname,id);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id){
        log.info("Delete Product:{}",id);
        this.productService.deleteProduct(id);
    }

    @DeleteMapping("/products/delete")
    public void deleteProductAll(@RequestBody ProductReq req){
        this.productService.deleteProductAll(req.getIds());
    }

    @GetMapping("/products/name")
    public List<Product> getProductsByName(@RequestParam("keyname")String keyname){
        List<Product> products = this.productService.findProducts("%" + keyname +"%");
        log.info("KeyName:{},products:{}",keyname,products);
        return products;
    }

    @GetMapping("/products/name/query")
    public List<Product> getProductByNameQuery(@RequestParam("keyname")String keyname){
        List<Product> products = this.productService.findProductsByQuery("%" + keyname + "%");
        log.info("KeyName:{},products:{}",keyname,products);
        return products;
    }

    @GetMapping("/products/name/raw")
    public List<Product> getProductByNameRaw(@RequestParam("keyname")String keyname){
        List<Product> products = this.productService.findProductsByRawQuery("%" + keyname + "%");
        log.info("KeyName:{},products:{}",keyname,products);
        return products;
    }

    //@CrossOrigin
    @PostMapping("/products/addCart")
    @ResponseBody
    public ResultData addCart(@RequestBody String productName) throws JSONException {
        System.out.println(productName);
        JSONArray jsonArray = null;
        jsonArray = new JSONArray("["+productName+"]");
        //JSONArray jsonArray1 = null;
        //jsonArray1 = new JSONArray("["+userName+"]");
        System.out.println(jsonArray.toString());
        System.out.println(jsonArray.getJSONObject(0).get("productName"));
        String realname = (String) jsonArray.getJSONObject(0).get("productName");
        //String realusername = (String) jsonArray.getJSONObject(0).get("userName");
        //System.out.println(realusername);
       // User user = this.userService.findUser(realusername);
        Product addedProduct = new Product();
        addedProduct = this.productService.findProductByName(realname);
        /*ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(addedProduct,productEntity);
        List<ProductEntity> productEntities = new ArrayList<ProductEntity>();
        productEntities.add(productEntity);*/
        BuyReq buyReq = new BuyReq();
        buyReq.setChecked(0);
        buyReq.setProductId(addedProduct.getId());
        buyReq.setProductNum(1);
        buyReq.setProductPrice(addedProduct.getProductPrice());
        buyReq.setUserId(1L);
        Buy buy = this.buyService.createOne(buyReq);
        //System.out.println(buy.toString());
        if(addedProduct == null){
            return ResultData.fail();
        }
        return ResultData.success();
    }

    @GetMapping("/cartList")
    public List<Buy> getBuys(){
        return this.buyService.getAll();
    }

    @PostMapping("/cartDel")
    @ResponseBody
    public ResultData deleteRecord(@RequestBody String productId) throws JSONException {
        JSONArray jsonArray = null;
        jsonArray = new JSONArray("["+productId+"]");
        System.out.println(jsonArray.getJSONObject(0).get("productId"));
        String realId = jsonArray.getJSONObject(0).get("productId").toString();
        //Long id = Long.valueOf(realId);
        this.buyService.deleteBuy(Long.valueOf(realId));
        return ResultData.success();
    }

    @PostMapping("/cartEdit")
    @ResponseBody
    public Buy editRecord(@RequestBody String productId,String productNum,String checked) throws JSONException {
        Buy editedBuy = new Buy();
        JSONArray jsonArray = null;
        jsonArray = new JSONArray("["+productId+"]");
        // System.out.println(jsonArray.getJSONObject(0).get("productName"));
        String realId = jsonArray.getJSONObject(0).get("productId").toString();
        Long id = Long.valueOf(realId);
        System.out.println(id);
        Integer realNum = (Integer) jsonArray.getJSONObject(0).get("productNum");
        System.out.println(realNum);
        Integer realchecked = (Integer) jsonArray.getJSONObject(0).get("checked");
        System.out.println(realchecked);
        editedBuy = this.buyService.editBuy(id,realNum,realchecked);
        return editedBuy;
    }

    @PostMapping("/editCheckAll")
    @ResponseBody
    public ResultData checkAll(@RequestBody String checkAllFlag) throws JSONException {
        /*Buy editedBuy = new Buy();
        JSONArray jsonArray = null;
        jsonArray = new JSONArray("["+productId+"]");
        // System.out.println(jsonArray.getJSONObject(0).get("productName"));
        String realId = jsonArray.getJSONObject(0).get("productId").toString();
        Long id = Long.valueOf(realId);
        System.out.println(id);
        Integer realNum = (Integer) jsonArray.getJSONObject(0).get("productNum");
        System.out.println(realNum);
        Integer realchecked = (Integer) jsonArray.getJSONObject(0).get("checked");
        System.out.println(realchecked);
        editedBuy = this.buyService.editBuy(id,realNum,realchecked);*/
        return ResultData.success();
    }

    @GetMapping("/orderrecords")
    @ResponseBody
    public List<Buy> getRecords(@RequestBody String userName){
        User user = this.userService.findUser(userName);
        Long userId = user.getId();
        List<Buy> buys = this.buyService.findRecords(userId);
        return buys;
    }
}
