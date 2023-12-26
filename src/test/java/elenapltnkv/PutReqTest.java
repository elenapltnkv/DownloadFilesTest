package elenapltnkv;

import com.fasterxml.jackson.databind.ObjectMapper;
import elenapltnkv.model.PutReq;
import org.junit.jupiter.api.Test;

import java.io.*;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class PutReqTest {
    @Test
    void parseJSONFile() throws IOException {
        File file = new File("src/test/resources/jsonRes/put_req.json");
        ObjectMapper m = new ObjectMapper();
        PutReq.Root root = m.readValue(file, PutReq.Root.class);

        assertThat(root.name).isEqualTo("PR Bank Card");
        assertThat(root.email).isEqualTo("email@test.ru");

    }
}
