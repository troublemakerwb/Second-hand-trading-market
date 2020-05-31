package com.example.demo.controller;

import com.example.demo.domain.Address;
import com.example.demo.domain.ResultData;
import com.example.demo.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/addressList")
    public List<Address> getAddresses(){
        return this.addressService.getAll();
    }

    @PostMapping("/setDefault")
    @ResponseBody
    public ResultData setDefault(@RequestBody String addressId) throws JSONException {
        JSONArray jsonArray = null;
        jsonArray = new JSONArray("["+addressId+"]");
        //System.out.println(jsonArray.getJSONObject(0).get("productId"));
        String realId = jsonArray.getJSONObject(0).get("addressId").toString();
        this.addressService.setDefault(Long.valueOf(realId));
        return ResultData.success();
    }

    @PostMapping("/addressDel")
    @ResponseBody
    public ResultData deleteAddress(@RequestBody String addressId) throws JSONException {
        JSONArray jsonArray = null;
        jsonArray = new JSONArray("["+addressId+"]");
        //System.out.println(jsonArray.getJSONObject(0).get("productId"));
        String realId = jsonArray.getJSONObject(0).get("addressId").toString();
        this.addressService.deleteAddress(Long.valueOf(realId));
        return ResultData.success();
    }

    @PostMapping("/addressAdd")
    @ResponseBody
    public ResultData addAddress(@RequestBody String username,@RequestBody String address,@RequestBody String tel) throws JSONException {
        JSONArray jsonArray = null;
        jsonArray = new JSONArray("["+username+address+tel+"]");
        //System.out.println(jsonArray.getJSONObject(0).get("productId"));
        String realaddress = jsonArray.getJSONObject(0).get("addressId").toString();
        String realusername = jsonArray.getJSONObject(0).get("username").toString();
        String realtel = jsonArray.getJSONObject(0).get("tel").toString();
        this.addressService.addAddress(realusername,realaddress,realtel);
        return ResultData.success();
    }
}
