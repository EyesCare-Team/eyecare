package com.project_EyeCare.EyeCare.Service;
import com.project_EyeCare.EyeCare.Repository.PdRepository;
import com.project_EyeCare.EyeCare.entity.PD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ItemService {

    private final PdRepository pdRepository;

    public void saveItem(String brand, String name, int price){
        PD pd = new PD();
        pd.setBrand(brand);
        pd.setName(name);
        pd.setPrice(price);
        pdRepository.save(pd);
    }

}
