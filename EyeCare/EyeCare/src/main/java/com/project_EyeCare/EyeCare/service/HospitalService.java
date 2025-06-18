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

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    @Value("${kakao.api.key}")
    private String kakaoApiKey;

    public List<HospitalEntity> findAndSaveNearby(double lat, double lng, String query) {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String url = String.format(
                "https://dapi.kakao.com/v2/local/search/keyword.json?query=%s&x=%f&y=%f&radius=10000",
                encodedQuery, lng, lat);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + kakaoApiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        List<HospitalEntity> result = new ArrayList<>();

        if (response.getStatusCode() == HttpStatus.OK) {
            JSONObject body = new JSONObject(response.getBody());
            JSONArray docs = body.getJSONArray("documents");

            for (int i = 0; i < docs.length(); i++) {
                JSONObject doc = docs.getJSONObject(i);
                String name = doc.getString("place_name");
                double y = Double.parseDouble(doc.getString("y"));
                double x = Double.parseDouble(doc.getString("x"));

                Optional<HospitalEntity> existing = hospitalRepository.findByNameAndLatAndLng(name, y, x);
                if (existing.isEmpty()) {
                    HospitalEntity saved = hospitalRepository.save(new HospitalEntity(null, name, y, x));
                    result.add(saved);
                } else {
                    result.add(existing.get());
                }
            }
        }
        System.out.println("최종 Kakao 요청 URL: " + url);
        System.out.println("응답 본문: " + response.getBody());

        return result;
    }

}
