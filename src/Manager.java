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
    public void fireNotification() {
        String message = createRandomizedMessage();
        if (message.substring(0, message.indexOf("|")).equals("Moon Phase")) {
            notification.displayTray(getDate() + ": Moon Phase", "Click notification to view", true, message.substring(message.indexOf("|") + 1));
        } else if (message.substring(0, message.indexOf("|")).equals("Star Chart")) {
            notification.displayTray(getDate() + ": Star Chart", "Click notification to view", true, message.substring(message.indexOf("|") + 1));
        } else {
            notification.displayTray(message.substring(0, message.indexOf("|")), message.substring(message.indexOf("|") + 1), false, null);
        }
    }
    public void fireEmail(String recipients) {
        String emailDetails = "Current Moon Phase: " + astroAPI.parseJSONMoonPhase(astroAPI.makeAPICallMoonPhase(getDate()));
        emailDetails += "\n\nCurrent Star Chart: " + astroAPI.parseJSONStarChart(astroAPI.makeAPICallStarChart(getDate())) + "\n\n";
        ArrayList<String> planetInfo = astroAPI.parseJSONPlanet(astroAPI.makeAPICallPlanet(getDate()));
        for (int i = 0; i < planetInfo.size(); i++) {
            String temp = planetInfo.get(i);
            String name = temp.substring(0, temp.indexOf("|"));
            temp = temp.substring(temp.indexOf("|") + 1);
            String distanceFromEarth = temp.substring(0, temp.indexOf("|"));
            temp = temp.substring(temp.indexOf("|") + 1);
            String rightAscension = temp.substring(0, temp.indexOf("|"));
            temp = temp.substring(temp.indexOf("|") + 1);
            String declination = temp.substring(0, temp.indexOf("|"));
            temp = temp.substring(temp.indexOf("|") + 1);
            String constellation = temp;
            emailDetails += name + " Info\nConstellation: " + constellation + "\nDistance From Earth: " + distanceFromEarth + "\nRight Ascension: " + rightAscension + "\nDeclination: " + declination + "\n\n";
        }
        email.sendEmail(emailDetails, recipients);
    }
    public String createRandomizedMessage() {
        int rand = (int) (Math.random() * 13);
        String date = getDate();
        ArrayList<String> planetInfo = new ArrayList<String>();
        System.out.println(date);
        if (rand <= 10) {
            planetInfo = astroAPI.parseJSONPlanet(astroAPI.makeAPICallPlanet(date));
            String temp = planetInfo.get(rand);
            String name = temp.substring(0, temp.indexOf("|"));
            temp = temp.substring(temp.indexOf("|") + 1);
            String distanceFromEarth = temp.substring(0, temp.indexOf("|"));
            temp = temp.substring(temp.indexOf("|") + 1);
            String rightAscension = temp.substring(0, temp.indexOf("|"));
            temp = temp.substring(temp.indexOf("|") + 1);
            String declination = temp.substring(0, temp.indexOf("|"));
            temp = temp.substring(temp.indexOf("|") + 1);
            String constellation = temp;
            return name + " Info\nConstellation: " + constellation + "|Distance From Earth: " + distanceFromEarth + "\nRA: " + rightAscension + "\nDec: " + declination;
        } else if (rand == 11) {
            return "Moon Phase|" + astroAPI.parseJSONMoonPhase(astroAPI.makeAPICallMoonPhase(date));
        } else if (rand == 12) {
            return "Star Chart|" + astroAPI.parseJSONStarChart(astroAPI.makeAPICallStarChart(date));
        }
        return "";
    }
    public String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now).replace("/", "-");
    }
}
/*
Size = 11
[Sun|1.5131696512655E8|10h 03m 36s|11° 52' 48"|Leo, Moon|400080.57324|06h 09m 00s|26° 43' 12"|Gemini, Mercury|1.5059057510193E8|11h 41m 24s|0° 32' 24"|Virgo, Venus|2.4398275515016E8|09h 01m 12s|17° 49' 12"|Cancer, Earth|6369.24827|20h 56m 24s|-41° 23' 24"|Microscopium, Mars|1.5175300263611E8|03h 55m 48s|18° 53' 24"|Taurus, Jupiter|6.1743450418971E8|00h 30m 00s|1° 32' 24"|Cetus, Saturn|1.3262227943815E9|21h 35m 24s|-16° 16' 12"|Capricornus, Uranus|2.91505583117845E9|03h 04m 48s|17° 0' 36"|Aries, Neptune|4.3386065726234E9|23h 41m 24s|-4° 39' 36"|Aquarius, Pluto|5.04614118670164E9|19h 55m 12s|-24° 58' 48"|Sagittarius]
https://widgets.astronomyapi.com/moon-phase/generated/4cc2e237d408669195184a6b9bad36c2dc8762103bb04fff738fa6f009c51fe9.png
https://widgets.astronomyapi.com/star-chart/generated/ee825b010a31a9306bd32906fb61d0415f68521046ee157487809666a4b35f14.png
 */