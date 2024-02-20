package com.sb.staging.controller;

import com.sb.common.domain.Agent;
import com.sb.staging.domain.Gicl;
import com.sb.staging.domain.Student;
import com.sb.staging.domain.Teacher;
import com.sb.staging.event.MyApplicationEvent;
import com.sb.staging.publish.MyApplicationEventPublisher;
import com.sb.staging.service.AgentService;
import com.sb.staging.service.AnimalService;
import com.sb.staging.service.GiclService;
import com.sb.staging.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("sb")
public class SbController {

    private final MyApplicationEventPublisher applicationEventPublisher;

    private final AnimalService animalService;

    private final GiclService giclService;

    private final AgentService agentService;

    private final KafkaTemplate kafkaTemplate;

    private final MongoTemplate mongoTemplate;

    @RequestMapping("/m1")
    public void m1() {
        Student student = new Student();
        student.setYear("2023");
        MyApplicationEvent myApplicationEvent = new MyApplicationEvent(student, "fgu", "1988");
        applicationEventPublisher.publishEvent(myApplicationEvent);
    }

    @RequestMapping("/m2")
    public ResponseEntity<Student> m2() {
        Student student = new Student();
        student.setYear("2023");
        return ResponseEntity.ok(student);
    }

    @RequestMapping("/getAnswer")
    public ResponseEntity<Teacher> m3(@RequestBody Teacher input) {
        Teacher teacher = new Teacher();
        teacher.setName(input.getName() + "#####");
        teacher.setName("Jack");
        teacher.setCode("1988");
        return ResponseEntity.ok(teacher);
    }

    @RequestMapping("/m4")
    public ResponseEntity<Teacher> m4() {
        Teacher teacher = new Teacher();
        //teacher.setName(input.getName() + "#####");
        teacher.setCode("1988" + animalService.getCategory("ABC"));
        return ResponseEntity.ok(teacher);
    }

    @RequestMapping("/m5")
    public ResponseEntity<List<Gicl>> getAllGicl() {
        return ResponseEntity.ok(giclService.selectAll());
    }

    @RequestMapping("/m6")
    public ResponseEntity<byte[]> getDownFile() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment;filename=test.zip;charset=utf-8");
        byte[] data = fileConvertToByteArray(new File("D:\\test.zip"));
        System.out.println(data.toString());
        ResponseEntity<byte[]> responseEntity = new ResponseEntity(data, httpHeaders, HttpStatus.OK);

        return responseEntity;
    }

    @RequestMapping("/m7")
    public ResponseEntity<byte[]> getDowntxtFile() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.add("Content-Disposition", "attachment;filename=fg.txt;charset=utf-8");
        ResponseEntity<byte[]> responseEntity = new ResponseEntity(fileConvertToByteArray(new File("D:\\fg.txt")), httpHeaders, HttpStatus.OK);

        return responseEntity;
    }

    @RequestMapping("/m8")
    public ResponseEntity<List<Agent>> getAllAgnt() {
        return ResponseEntity.ok(agentService.selectAll());
    }


    @RequestMapping("/m9")
    public void test1() {
        Teacher teacher = new Teacher("jackson", "110");
        for (int i = 0; i < 1000; i++) {
            //kafkaTemplate.send("sb_topic_1", ObjectUtils.getDataJson(teacher));
            kafkaTemplate.send("sb_topic_1", ObjectUtils.getDataJson(teacher));
        }
    }

    @RequestMapping("/m10")
    public void test2() {
        Teacher teacher = new Teacher("jackson", "110");
        for (int i = 0; i < 1000; i++) {
            //kafkaTemplate.send("sb_topic_1", ObjectUtils.getDataJson(teacher));
            kafkaTemplate.send("sb_topic_3", ObjectUtils.getDataJson(teacher));
        }
    }

    @RequestMapping("/mg1")
    public void mg1() {
        Student student = new Student();
        student.setId(6l);
        student.setName("宋人头");
        mongoTemplate.save(student);
    }

    @RequestMapping("/mg2")
    public void mg2() {
        Student student = new Student();
        student.setId(6l);
        student.setName("宋人头XX");
        Query query = new Query(Criteria.where("id").is(student.getId()));

        //修改的内容
        Update update = new Update();
        update.set("name", student.getName());

        mongoTemplate.updateFirst(query, update, Student.class);
    }

    /**
     * 把一个文件转化为byte字节数组。
     *
     * @return
     */
    private byte[] fileConvertToByteArray(File file) {
        byte[] data;
        try (InputStream inputStream = Files.newInputStream(file.toPath());
             BufferedInputStream bis = new BufferedInputStream(inputStream);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            int len;
            byte[] buffer = new byte[1024];
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            inputStream.close();
            bis.close();
            data = bos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return data;
    }
}