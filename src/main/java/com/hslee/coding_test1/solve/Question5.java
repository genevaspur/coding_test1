package com.hslee.coding_test1.solve;

import com.hslee.coding_test1.dto.Question5DTO;
import com.hslee.coding_test1.dto.QuoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @see com.hslee.coding_test1.utility.WebClientConfig
 */
@Service
public class Question5 {

    @Autowired
    WebClient webClient;

    public List<Question5DTO> solve() {
        List<Question5DTO> resultList = new ArrayList<>();
        List<QuoteDTO>  quoteDTOList = new ArrayList<>();
        try {

            for (int i = 0; i < 100; i++) {
                QuoteDTO quoteDTO = webClient.get()
                        .exchangeToMono(response -> response.bodyToMono(QuoteDTO.class))
                        .block();
                quoteDTOList.add(quoteDTO);
            }

            for (QuoteDTO quoteDTO : quoteDTOList) {
                boolean isExist = false;
                for (Question5DTO question5DTO : resultList) {
                    if (Objects.equals(question5DTO.getItem().getId(), quoteDTO.getId())) {
                        question5DTO.setCount(question5DTO.getCount() + 1);
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    Question5DTO question5DTO = new Question5DTO();
                    question5DTO.setCount(1);
                    question5DTO.setItem(quoteDTO);
                    resultList.add(question5DTO);
                }

                resultList.sort((o1, o2) -> o2.getCount() - o1.getCount());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }
}
