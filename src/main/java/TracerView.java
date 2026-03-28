import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;

public class TracerView {
    public static void main(String[] Args) throws InterruptedException {
        Playwright playWright = Playwright.create();

        BrowserType.LaunchOptions lp = new BrowserType.LaunchOptions();
        lp.setHeadless(false);
        lp.setChannel("webkit");

        Browser browser = playWright.webkit().launch(lp);
        BrowserContext context = browser.newContext();

        // Start tracing before creating / navigating a page.
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        try {
            Page page = context.newPage();
            page.navigate("https://www.samsung.com/in/");
            page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Mobile")).first().click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Galaxy S").setExact(true)).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sort Online Availability")).click();
            page.getByText("Recommended", new Page.GetByTextOptions().setExact(true)).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Buy:Galaxy S26+").setExact(true)).click();
            page.locator("span").filter(new Locator.FilterOptions().setHasText("Galaxy S26 15.93 cm display")).nth(1).click();

            // Stop tracing and export it into a zip archive.
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("trace.zip")));


        }catch (Exception e){
            browser.close();
            playWright.close();
        }
        browser.close();
        playWright.close();
    }
}
