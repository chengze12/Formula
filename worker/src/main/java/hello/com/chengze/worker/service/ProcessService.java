package hello.com.chengze.worker.service;

import com.chengze.domain.User;
import com.chengze.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class ProcessService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @JmsListener(destination = "Formula_queue_dev")
    public void processMessage(String message) throws IOException{
        Map<Object, Object> map= convertStringToMap(message);
        String messageBody = (String) map.get("messageKey");
        Long id = Long.valueOf((String) map.get("object_id")).longValue();
        User user= userService.findById(id);
        String email = user.getEmail();
        String sendemail="chengzewang612@gmail.com";
        System.out.println(message);
    }

    private Map<Object, Object> convertStringToMap(String message) {
        Map<Object, Object> map = null;
        ObjectMapper mapper = new ObjectMapper();
        // convert JSON string to Map
        try {
            map = mapper.readValue(message, new TypeReference<Map<String, String>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
