import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Manager {
    AstroAPI astroAPI;
    Notification notification;
    Email email;

    public Manager() {
        astroAPI = new AstroAPI();
        notification = new Notification();
        email = new Email();
    }
    public String createRandomizedMessage() {
        int rand = (int) (Math.random() * 13);
        String date = getDate();
        String message = "";
        ArrayList<String> planetInfo = new ArrayList<String>();

        if (rand <= 10) {
            planetInfo = astroAPI.parseJSONPlanet(astroAPI.makeAPICallPlanet(date));
            String name = planetInfo.get(rand).substring(0, planetInfo.get(rand).indexOf("|"));
        } else if (rand == 11) {
            return astroAPI.parseJSONMoonPhase(astroAPI.makeAPICallMoonPhase(date));
        } else if (rand == 12) {
            return astroAPI.parseJSONStarChart(astroAPI.makeAPICallStarChart(date));
        }
        return "";
    }
    public String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
/*
Size = 11
[Sun|1.5131696512655E8|10h 03m 36s|11° 52' 48"|Leo, Moon|400080.57324|06h 09m 00s|26° 43' 12"|Gemini, Mercury|1.5059057510193E8|11h 41m 24s|0° 32' 24"|Virgo, Venus|2.4398275515016E8|09h 01m 12s|17° 49' 12"|Cancer, Earth|6369.24827|20h 56m 24s|-41° 23' 24"|Microscopium, Mars|1.5175300263611E8|03h 55m 48s|18° 53' 24"|Taurus, Jupiter|6.1743450418971E8|00h 30m 00s|1° 32' 24"|Cetus, Saturn|1.3262227943815E9|21h 35m 24s|-16° 16' 12"|Capricornus, Uranus|2.91505583117845E9|03h 04m 48s|17° 0' 36"|Aries, Neptune|4.3386065726234E9|23h 41m 24s|-4° 39' 36"|Aquarius, Pluto|5.04614118670164E9|19h 55m 12s|-24° 58' 48"|Sagittarius]
https://widgets.astronomyapi.com/moon-phase/generated/4cc2e237d408669195184a6b9bad36c2dc8762103bb04fff738fa6f009c51fe9.png
https://widgets.astronomyapi.com/star-chart/generated/ee825b010a31a9306bd32906fb61d0415f68521046ee157487809666a4b35f14.png
 */