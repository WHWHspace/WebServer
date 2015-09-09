import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Created by YBH on 2015/9/10.
 */
public class ServletProcessor {

    public void process(Request request, Response response) {
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf('/') + 1);
        URLClassLoader loader = null;

        try {
            //create a URLClassLoader
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File((Constants.WEB_ROOT));

            String repository = (new URL("file",null,classPath.getCanonicalPath() + File.separator)).toString();

            urls[0] = new URL(null,repository,streamHandler);
            loader = new URLClassLoader(urls);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        Class myClass = null;
        try {
            myClass = loader.loadClass(servletName);
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        Servlet servlet = null;
        try {
            servlet = (Servlet)myClass.newInstance();
            servlet.service((ServletRequest)request,(ServletResponse)response);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        catch (Throwable e){
            e.printStackTrace();
        }

    }
}