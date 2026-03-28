import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;

public class MultipleBrowserContext {
    public static void main(String[] Args) throws InterruptedException {
        Playwright playWright = Playwright.create();

        BrowserType.LaunchOptions lp = new BrowserType.LaunchOptions();
        lp.setHeadless(false);
        lp.setChannel("webkit");

        Browser browser = playWright.webkit().launch(lp);


        try {
            BrowserContext context1 = browser.newContext();
            Page page1 = context1.newPage();
            page1.navigate("https://www.samsung.com/in/");
            System.out.println(page1.title());

            BrowserContext context2 = browser.newContext();
            Page page2 = context2.newPage();
            page2.navigate("https://www.amazon.in/");
            System.out.println(page2.title());

            BrowserContext context3 = browser.newContext();
            Page page3 = context1.newPage();
            page3.navigate("https://www.facebook.com/");
            System.out.println(page3.title());


        }catch (Exception e){
            browser.close();
            playWright.close();
        }
        browser.close();
        playWright.close();
    }
}
