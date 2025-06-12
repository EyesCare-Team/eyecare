package com.project_EyeCare.EyeCare.controller;

import com.project_EyeCare.EyeCare.Repository.ItemRepository;
import com.project_EyeCare.EyeCare.Repository.PdRepository;
import com.project_EyeCare.EyeCare.entity.PD;
import com.project_EyeCare.EyeCare.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @Autowired
    private final PdRepository pdRepository;
    private final ItemRepository itemRepository;

    @GetMapping("/products")
    public String productLIst(Model model) {
        List<PD> productList = pdRepository.findAll();
        model.addAttribute("products", productList);
        return "product/products";
    }



    //상품추가페이지
    @GetMapping("/write")
    String write(Model model) {
        List<PD> productList = pdRepository.findAll();
        model.addAttribute("products", productList);
        return "/admin/adminproduct";
    }


    // 상품 수정 페이지 (GET) - 상품 ID를 받아 상품 정보를 model에 전달
    @GetMapping("/products/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Optional<PD> optionalProduct = pdRepository.findById(id);
        if (optionalProduct.isPresent()) {
            PD product = optionalProduct.get();
            model.addAttribute("product", product);
            return "admin/edit"; // .html 확장자 생략! 템플릿 경로만 씁니다.
        } else {
            return "redirect:/products";
        }
    }


    //상품 추가에 대한 내용
    @PostMapping("/add")
    String addPost(String brand,String name,int price){
        //ItemService 에서 불러옴
        itemService.saveItem(brand,name,price);
        return "redirect:/write";
    }


     //상품 추가, 수정, 삭제 처리 메소드

    // 상품 수정 처리
    @PostMapping("/products/edit/{id}")
    public String updateProduct(
            @PathVariable int id,
            @RequestParam String brand,
            @RequestParam String name,
            @RequestParam int price
    ) {
        // 해당 상품 찾아오기
        Optional<PD> optionalProduct = pdRepository.findById(id);
        if (optionalProduct.isPresent()) {
            PD product = optionalProduct.get();
            product.setBrand(brand);
            product.setName(name);
            product.setPrice(price);
            pdRepository.save(product); // DB에 업데이트
        }
        // 수정이 끝나면 상품 관리 페이지로 이동
        return "redirect:/write";
    }



    //상품 detail 페이지 상품 ID로 데이터 불러오기
    @GetMapping("/products/{id}")
    public String productDetail(@PathVariable int id, Model model) {
        Optional<PD> detail = pdRepository.findById(id);
        if (detail.isPresent()) {
            PD product = detail.get();
            model.addAttribute("product", product);
        } else {
            return "redirect:/products";
        }
        return "product/detail";

    }

    // 삭제 처리
    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        pdRepository.deleteById(id);
    return "redirect:/write";
    }






}

