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

@Controller	// This means that this class is a Controller
@RequestMapping(path="/proxy") // This means URL's start with /resv (after Application path)
@CrossOrigin(origins = "*")
public class ProxyController {

	private static final Logger log = LoggerFactory.getLogger(ProxyController.class);

	@GetMapping(path="/jnse-rcmd-info")
        public @ResponseBody String getJnseRcmdInfo(
			@RequestParam(value = "apiKey") String apiKey,
			@RequestParam(value = "age") String age,
			@RequestParam(value = "rentGrntAmt") String rentGrntAmt,
			@RequestParam(value = "addr1") String addr1,
			@RequestParam(value = "addr2") String addr2,

			@RequestParam(value = "weddStcd") String weddStcd,
			@RequestParam(value = "myIncmAmt") String myIncmAmt,
			@RequestParam(value = "myTotDebtAmt") String myTotDebtAmt,
			@RequestParam(value = "sposAnnlIncmAmt") String sposAnnlIncmAmt,
			@RequestParam(value = "sposDebtAmt") String sposDebtAmt,
			@RequestParam(value = "ownHsCnt") String ownHsCnt,
			@RequestParam(value = "chldCnt") String chldCnt,

			@RequestParam(value = "sngpHhldYn") String sngpHhldYn,
			@RequestParam(value = "plcyCmnrFinUseYn") String plcyCmnrFinUseYn,
			@RequestParam(value = "petySoweSftprYn") String petySoweSftprYn,
			@RequestParam(value = "crdtRcvrSprtYn") String crdtRcvrSprtYn,
			@RequestParam(value = "soclCnsdCndtYn") String soclCnsdCndtYn,
			@RequestParam(value = "dsblHhldYn") String dsblHhldYn,
			@RequestParam(value = "ntonMrorHhldYn") String ntonMrorHhldYn,
			@RequestParam(value = "kwrcHhldYn") String kwrcHhldYn,
			@RequestParam(value = "mlfmYn") String mlfmYn
			) {

		log.info("apiKey = " + apiKey);

		String url = "https://openapi.hf.go.kr:10880/jnse-rcmd-info/jnse-rcmd-list?"
			+ "dataType=json"
			+ "&SG_APIM=" + apiKey
			+ "&age=" + age
			+ "&rentGrntAmt=" + rentGrntAmt
			+ "&addr1=" + addr1 
			+ "&addr2=" + addr2 

			+ "&weddStcd=" + weddStcd 
			+ "&myIncmAmt=" + myIncmAmt 
			+ "&myTotDebtAmt=" + myTotDebtAmt 
			+ "&sposAnnlIncmAmt=" + sposAnnlIncmAmt 
			+ "&sposDebtAmt=" + sposDebtAmt 
			+ "&ownHsCnt=" + ownHsCnt
			+ "&chldCnt=" + chldCnt

			+ "&sngpHhldYn=" + sngpHhldYn 
			+ "&plcyCmnrFinUseYn=" + plcyCmnrFinUseYn 
			+ "&petySoweSftprYn=" + petySoweSftprYn 
			+ "&crdtRcvrSprtYn=" + crdtRcvrSprtYn 
			+ "&soclCnsdCndtYn=" + soclCnsdCndtYn 
			+ "&dsblHhldYn=" + dsblHhldYn 
			+ "&ntonMrorHhldYn=" + ntonMrorHhldYn 
			+ "&kwrcHhldYn=" + kwrcHhldYn 
			+ "&mlfmYn=" + mlfmYn
			;

		RestTemplate restTemplate = new RestTemplate();
		String rspsStr = restTemplate.getForObject(url, String.class);
		log.info(rspsStr.toString());

                return rspsStr;
        }
}
