package com.project_EyeCare.EyeCare.service;

import com.project_EyeCare.EyeCare.entity.HospitalEntity;
import com.project_EyeCare.EyeCare.Repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    @Value("${kakao.api.key}")
    private String kakaoRestApiKey;

    public List<HospitalEntity> findAndSaveNearby(double lat, double lng) {
        // Kakao 카테고리 검색 API: HP8 = 병원
        String url = String.format(
                "https://dapi.kakao.com/v2/local/search/category.json?category_group_code=HP8&x=%f&y=%f&radius=20000",
                lng, lat);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + kakaoRestApiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        List<HospitalEntity> result = new ArrayList<>();

        System.out.println("최종 Kakao 요청 URL: " + url);
        System.out.println("응답 코드: " + response.getStatusCode());
        System.out.println("Loaded Kakao API Key: " + kakaoRestApiKey);

        if (response.getStatusCode() == HttpStatus.OK) {
            JSONObject body = new JSONObject(response.getBody());
            JSONArray docs = body.getJSONArray("documents");

            for (int i = 0; i < docs.length(); i++) {
                JSONObject doc = docs.getJSONObject(i);

                String name = doc.getString("place_name");
                double y = Double.parseDouble(doc.getString("y"));
                double x = Double.parseDouble(doc.getString("x"));
                String address = doc.optString("address_name", "");
                String roadAddress = doc.optString("road_address_name", "");
                String placeUrl = doc.optString("place_url", "");

                Optional<HospitalEntity> existing = hospitalRepository.findByNameAndLatAndLng(name, y, x);

                HospitalEntity hospital = existing.orElse(new HospitalEntity());

                hospital.setName(name);
                hospital.setLat(y);
                hospital.setLng(x);
                hospital.setAddress(address);
                hospital.setRoadAddress(roadAddress);
                hospital.setPlaceUrl(placeUrl);

                hospitalRepository.save(hospital);
                result.add(hospital);
            }

            System.out.println("응답 본문: " + response.getBody());
        }

        return result;
    }
}
