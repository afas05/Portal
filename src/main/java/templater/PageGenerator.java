package templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.Map;
/**
 * Created by Игорь on 01.11.2016.
 */
public class PageGenerator {

    private static PageGenerator pageGenerator;
    private Configuration cfg;

    public static PageGenerator instance() {
        if (pageGenerator == null)
            pageGenerator = new PageGenerator();
        return pageGenerator;
    }

    public String getPage(String filename, Map<String, Object> data) {
        Writer stream = new StringWriter();
        try {
            Template template = cfg.getTemplate(filename);
            template.process(data, stream);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return stream.toString();
    }

    private PageGenerator() {
        cfg = setCfg();
    }

    private Configuration setCfg(){
        cfg = new Configuration(Configuration.VERSION_2_3_20);
        try {
            cfg.setDirectoryForTemplateLoading(new File("templates/"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cfg;
    }
}
