package ibf2022.batch2.csf.server.controllers;

import java.io.StringReader;
import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Controller
@RequestMapping(path="/api")
//@CrossOrigin(origins="*")
public class CapitalizeController {

    // GET /uppercase?msg=some message
    @GetMapping(path="/uppercase", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getUpperCase(@RequestParam String msg) {
        JsonObject response = Json.createObjectBuilder()
            .add("text", msg.toUpperCase())
            .add("timestamp", (new Date()).toString())
            .build();
        return ResponseEntity.ok(response.toString());
    }

    // POST /uppercase
    // { msg: "hello, world" }
    @PostMapping(path="/uppercase", 
            consumes = MediaType.APPLICATION_JSON_VALUE, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> postUpperCase(@RequestBody String payload) {
        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonObject jsonPayload = reader.readObject();

        String msg = jsonPayload.getString("msg");

        JsonObject response = Json.createObjectBuilder()
            .add("text", msg.toUpperCase())
            .add("timestamp", (new Date()).toString())
            .build();
        return ResponseEntity.ok(response.toString());
    }

    // POST /uppercase
    // form-urlencoded
    @PostMapping(path="/uppercase"
        , consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
        , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> postUpperCaseForm(@RequestBody MultiValueMap<String, String> form) {
        String msg = form.getFirst("msg");

        System.out.printf("FORM URLENCODED: %s\n", msg);

        JsonObject response = Json.createObjectBuilder()
            .add("text", msg.toUpperCase())
            .add("timestamp", (new Date()).toString())
            .build();
        return ResponseEntity.ok(response.toString());
    }
}
