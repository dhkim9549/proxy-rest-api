package com.example.proxyrestapi;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping(path="/hf-api-proxy")
@CrossOrigin(origins = "*")
public class ProxyController {

	private static final Logger log = LoggerFactory.getLogger(ProxyController.class);

	@Autowired
	private TbApiKeyRepository tbApiKeyRepository;

	private String getApiKey() {
		
		Optional<TbApiKey> optApiKey = tbApiKeyRepository.findById(0);
                String apiKey = optApiKey.orElse(null).getApiKey();
                log.info("apiKey = " + apiKey);

		return apiKey;
	}

	@GetMapping(path="/jnse-rcmd-info", produces="application/json")
        public @ResponseBody String getJnseRcmdInfo(
			@RequestParam(value = "rentGrntAmt") String rentGrntAmt,
			@RequestParam(value = "trgtLwdgCd") String trgtLwdgCd,
			@RequestParam(value = "age") String age,
			@RequestParam(value = "weddStcd", defaultValue = "") String weddStcd,
			@RequestParam(value = "myIncmAmt", defaultValue = "0") String myIncmAmt,
			@RequestParam(value = "myTotDebtAmt", defaultValue = "0") String myTotDebtAmt,
			@RequestParam(value = "ownHsCnt", defaultValue = "0") String ownHsCnt,
			@RequestParam(value = "grntPrmeActnDvcdCont", defaultValue = "") String grntPrmeActnDvcdCont
			) {

		String apiKey = getApiKey(); 

		String url = "https://openapi.hf.go.kr:10880/jnse-rcmd-info/jnse-rcmd-list?"
			+ "dataType=json"
			+ "&SG_APIM=" + apiKey
			+ "&pageNo=1"
			+ "&numOfRows=100"

			+ "&rentGrntAmt=" + rentGrntAmt
			+ "&trgtLwdgCd=" + trgtLwdgCd 
			+ "&age=" + age 

			+ "&weddStcd=" + weddStcd 
			+ "&myIncmAmt=" + myIncmAmt 
			+ "&myTotDebtAmt=" + myTotDebtAmt 
			+ "&ownHsCnt=" + ownHsCnt
			+ "&grntPrmeActnDvcdCont=" + grntPrmeActnDvcdCont 
			;

		log.info("url = " + url);

		RestTemplate restTemplate = new RestTemplate();
		String rspsStr = restTemplate.getForObject(url, String.class);
		log.info(rspsStr.toString());

                return rspsStr;
        }

	@GetMapping(path="/jnse-max-rent-amt-list", produces="application/json")
        public @ResponseBody String getJnseMaxRentMatList(
                        @RequestParam(value = "grntDvcd") String grntDvcd
                        ) {

                String apiKey = getApiKey();

                String url = "https://openapi.hf.go.kr:10880/jnse-rcmd-info/jnse-max-rent-amt-list?"
                        + "dataType=json"
                        + "&SG_APIM=" + apiKey
                        + "&pageNo=1"
                        + "&numOfRows=100"
                        + "&grntDvcd=" + grntDvcd 
                        ;

                log.info("url = " + url);

                RestTemplate restTemplate = new RestTemplate();
                String rspsStr = restTemplate.getForObject(url, String.class);
                log.info(rspsStr.toString());

                return rspsStr;
        }

	@GetMapping(path="/jnse-grtd-loan-rat-list", produces="application/json")
        public @ResponseBody String getJnseGrtdLoanRatList(
                        @RequestParam(value = "loanYm") String loanYm 
                        ) {

                String apiKey = getApiKey();

                String url = "https://openapi.hf.go.kr:10880/jnse-rcmd-info/jnse-grtd-loan-rat-list?"
                        + "dataType=json"
                        + "&SG_APIM=" + apiKey
                        + "&loanYm=" + loanYm 
                        ;

                log.info("url = " + url);

                RestTemplate restTemplate = new RestTemplate();
                String rspsStr = restTemplate.getForObject(url, String.class);
                log.info(rspsStr.toString());

                return rspsStr;
        }

	@GetMapping(path="/jnse-prod-dtl-info", produces="application/json")
        public @ResponseBody String getJnseProdDtlInfo(
                        @RequestParam(value = "grntDvcd") String grntDvcd
                        ) {

                String apiKey = getApiKey();

                String url = "https://openapi.hf.go.kr:10880/jnse-rcmd-info/jnse-prod-dtl-info?"
                        + "dataType=json"
                        + "&SG_APIM=" + apiKey
                        + "&grntDvcd=" + grntDvcd
                        ;

                log.info("url = " + url);

                RestTemplate restTemplate = new RestTemplate();
                String rspsStr = restTemplate.getForObject(url, String.class);
                log.info(rspsStr.toString());

                return rspsStr;
        }
}
