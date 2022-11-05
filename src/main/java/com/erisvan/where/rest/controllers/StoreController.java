package com.erisvan.where.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.erisvan.where.model.Calling;
import com.erisvan.where.model.Store;
import com.erisvan.where.service.CallingService;
import com.erisvan.where.service.StoreService;

@Controller
@RequestMapping("/store")
public class StoreController {

    @Autowired
    @Qualifier("storeServiceImpl")
    StoreService storeService;

    @Autowired
    @Qualifier("callingServiceImpl")
    CallingService callingService;

    @RequestMapping("/createStoreForm")
    public String createStoreForm(Model model) {
        model.addAttribute("store", new Store());
        return "store/createStore";
    }

    @RequestMapping("/createStore")
    public String createStore(@ModelAttribute("store") Store store, Model model) {
        storeService.createStore(store);
        return getAllStores(model);
    }

    @RequestMapping("/deleteStore/{idStore}")
    public String deleteStore(@PathVariable String idStore, Model model) {
        Store store = storeService.getStoreById(Integer.parseInt(idStore));
        storeService.deleteStore(store);
        return getAllStores(model);
    }

    @RequestMapping("/getStore/{idStore}")
    public String getStore(@PathVariable String idStore, Model model) {
        Store store = storeService.getStoreById(Integer.parseInt(idStore));
        model.addAttribute("store", store);
        model.addAttribute("callings", callingService.getAllCallings());
        return "store/getStore";
    }

    @RequestMapping("/acceptCalling/{idStore}/{idCalling}")
    public String acceptCalling(@PathVariable String idStore, @PathVariable String idCalling, Model model) {
        Store store = storeService.getStoreById(Integer.parseInt(idStore));
        Calling calling = callingService.getCallingById(Integer.parseInt(idCalling));

        List<Store> stores = calling.getStores();
        stores.add(store);
        calling.setStores(stores);
        callingService.createCalling(calling);

        model.addAttribute("store", store);
        model.addAttribute("callings", callingService.getAllCallings());
        return "store/getStore";
    }

    @RequestMapping("/getAllStores")
    public String getAllStores(Model model) {
        model.addAttribute("stores", storeService.getAllStores());
        return "store/getAllStores";
    }
}
