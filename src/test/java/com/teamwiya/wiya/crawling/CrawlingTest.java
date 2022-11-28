package com.teamwiya.wiya.crawling;

import com.teamwiya.wiya.util.CrawlingService;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class CrawlingTest {

    @Autowired
    CrawlingService crawlingService;

    private static List<String> pageXPaths = List.of(
            "//*[@id=\"info.search.page.no1\"]",
            "//*[@id=\"info.search.page.no2\"]",
            "//*[@id=\"info.search.page.no3\"]",
            "//*[@id=\"info.search.page.no4\"]",
            "//*[@id=\"info.search.page.no5\"]");

    @Test
    void crawlingRun() {
        //크롬 열기
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        WebDriver mainDriver = new ChromeDriver(options);
        WebDriver detailDriver = new ChromeDriver(options);
        // 임시로 자료담을 곳
        List<HashMap<String, String>> list = new ArrayList<>();

        try {
            // 페이지 열기
            mainDriver.get("https://map.kakao.com/");
            // 10초 쉬기
            Thread.sleep(10000);
            // 검색어 입력 후 엔터
            mainDriver.findElement(By.xpath("//*[@id=\"search.keyword.query\"]")).sendKeys("동물병원");
            mainDriver.findElement(By.xpath("//*[@id=\"search.keyword.query\"]")).sendKeys(Keys.ENTER);
            // 1.5초 쉬기
            Thread.sleep(1500);
            // 펼쳐보기 클릭
            mainDriver.findElement(By.xpath("//*[@id=\"info.search.place.more\"]")).sendKeys(Keys.ENTER);
            //1.5초 클릭
            Thread.sleep(1500);
            // 인기도순 클릭
            mainDriver.findElement(By.xpath("//*[@id=\"info.search.place.sort\"]/li[2]/a")).sendKeys(Keys.ENTER);
            //1.5초 클릭
            Thread.sleep(1500);
            // 다음 페이지 그룹 7번
            for (int i = 0; i < 7; i++) {
                // 페이지 2~5 클릭
                for (String pageXPath : pageXPaths) {
                    // 1.5초 쉬기
                    Thread.sleep(1000);
                    // 다음 페이지 클릭
                    mainDriver.findElement(By.xpath(pageXPath)).sendKeys(Keys.ENTER);
                    // 1.5초 쉬기
                    Thread.sleep(1000);
                    // 상세보기 요소들 가져오기
                    List<WebElement> detailElements = mainDriver.findElements(By.className("moreview"));
                    // 1.5초 쉬기
                    Thread.sleep(1000);
                    for (WebElement detail : detailElements) {
                        try {
                            // 하나의 병원 정보를 담을 리스트
                            HashMap<String, String> temp = new HashMap<>();
                            // 상세보기 주소 얻어와서 이동
                            String href = detail.getAttribute("href");
                            detailDriver.navigate().to(href);
                            Thread.sleep(1000);
                            //병원명
                            WebElement name = detailDriver.findElement(By.cssSelector(".inner_place .tit_location"));
                            String n = name.getText();
                            temp.put("hosName", name.getText());
                            //병원 주소
                            String sigu = detailDriver.findElement(By.className("txt_address")).getText()
                                    .replaceAll("구 .*", "구 ");
                            String jibun = detailDriver.findElement(By.className("txt_addrnum")).getText()
                                    .replaceAll("지번", "");
                            temp.put("hosAddress", sigu+jibun);
                            //전화번호
                            List<WebElement> txt_contact = detailDriver.findElements(By.className("txt_contact"));
                            if (!txt_contact.isEmpty()){
                                temp.put("hosPhone", txt_contact.get(0).getText());
                            }

                            List<WebElement> bg_presents = detailDriver.findElements(By.className("bg_present"));
                            List<WebElement> link_photos = detailDriver.findElements(By.className("link_photo"));
                            if (!bg_presents.isEmpty()) {
                                String bg_present = bg_presents.get(0).getCssValue("background-image")
                                        .replaceFirst("^url\\(\"", "")
                                        .replaceFirst("\"\\)$", "");
                                temp.put("himPath", bg_present);
                            } else if (!link_photos.isEmpty()) {
                                String link_photo = link_photos.get(0).getCssValue("background-image")
                                        .replaceFirst("^url\\(\"", "")
                                        .replaceFirst("\"\\)$", "");
                                temp.put("himPath", link_photo);
                            }
                            list.add(temp);
                        } catch (Exception e) {
                            System.out.println("병원 안들어감 ");

                        }
                    }
                }
                // 다음 페이지들 보기 클릭
                mainDriver.findElement(By.xpath("//*[@id=\"info.search.page.next\"]"))
                        .sendKeys(Keys.ENTER);
            }

        } catch (Exception e) {
            System.out.println("=========================================================");
        } finally {
            mainDriver.quit();
            detailDriver.quit();
        }


        for (HashMap<String, String> stringStringHashMap : list) {
            System.out.println("stringStringHashMap = " + stringStringHashMap);
            try {
                crawlingService.save(stringStringHashMap);
            } catch (Exception e) {
                System.out.println("============================");
            }
        }
    }
}
