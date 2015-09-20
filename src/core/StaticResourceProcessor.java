package core;

import connector.Request;
import connector.Response;

import java.io.IOException;

/**
 * 静态资源请求处理器
 */
public class StaticResourceProcessor {

    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
