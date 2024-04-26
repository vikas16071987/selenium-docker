package com.vikasdocker.util;
import com.vikasdocker.vendorportal.model.VendorPortalTestData;

public class Demo {

    public static void main(String[] args) throws Exception {

        /*VendorPortalTestData testData = JsonUtil.getTestData("test-data/vendor-portal/john.json", VendorPortalTestData.class);
        System.out.println(testData.annualEarning());*/
        System.setProperty("browser", "firefox");
        Config.initialize();
        System.out.println(Config.get("vendorPortal.url"));
        
    }

}






//InputStream stream = ResourceLoader.getResource("selenium-docker/a/dummy.txt");
        //String Content = IOUtils.toString(stream, StandardCharsets.UTF_8);
        //System.out.println(Content);