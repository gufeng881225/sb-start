package com.sb.staging.velocity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class VelocityTest {

    public static void main(String[] args) throws IOException {
        VelocityContext context = new VelocityContext();
        Map map1 = Maps.newHashMap();
        map1.put("k1","v1");
        Map map2 = Maps.newHashMap();
        map2.put("k2","v2");
        List value1 = Lists.newArrayList(map1, map2);

        Map map3 = Maps.newHashMap();
        map3.put("attr1",value1);
        context.put("input", map3);
        Path path = Paths.get(new DefaultResourceLoader().getResource("velocity/v1.vm").getURI());
        String spec = new String(Files.readAllBytes(path));
        String convertedSpec = convertTemplate(spec, context);
        System.out.println(convertedSpec);

    }

    public static String convertTemplate(String str, VelocityContext context) {
        StringWriter writer = new StringWriter();
        Velocity.evaluate(context, writer, "velocityLogTag", str);
        return writer.toString();
    }
}
