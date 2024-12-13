package vttp.batch5.ssf.noticeboard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import jakarta.json.JsonObject;
import vttp.batch5.ssf.noticeboard.configuration.VariableConfig;
import vttp.batch5.ssf.noticeboard.models.Notice;
import vttp.batch5.ssf.noticeboard.repositories.NoticeRepository;
import vttp.batch5.ssf.noticeboard.utils.JsonManagement;

@Service
public class NoticeService {

	@Autowired
	private NoticeRepository noticeRepo;

	@Autowired
	private VariableConfig constant; 

	public String[] postToNoticeServer(Notice notice, JsonObject json) {

		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(json.toString(), header);


		try {
        ResponseEntity<String> response = restTemplate.postForEntity(constant.getServer_url(), requestEntity, String.class);
        	if (response.getStatusCode() == HttpStatus.OK) {
            String noticeId = response.getBody();
            JsonObject responseBody = JsonManagement.parseToJson(noticeId);
            String id = JsonManagement.fetchID(responseBody);
            long timestamp = JsonManagement.fetchTimestamp(responseBody);
            noticeRepo.insertNotices(id, timestamp);
            return new String[] {"success", id};
        	}

        return new String[] {"false", "false"};
		
    } catch (HttpClientErrorException.BadRequest e) {

        return new String[] {"false", e.getMessage()};

    } catch (HttpServerErrorException e) {

		return new String[] {"false", e.getMessage()};

    } catch (Exception e) {

        return new String[] {"false", e.getMessage()};
    }
}

}
