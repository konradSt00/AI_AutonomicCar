package FuzzyLogic;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class FuzzyLogic {
    public static final String RULES_FILENAME = "src/main/resources/rules.fcl";
    public FIS fis;
    public void setVars(int distX, double speedX, int distY){ //update variables
        fis.setVariable("distX", distX);
        fis.setVariable("speedX", speedX);
        fis.setVariable("distY", distY);
    }
    public void setFis(){ // load rules file
        try {
            fis = FIS.load(RULES_FILENAME, false);

        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out
                    .println("Niepoprawna liczba parametrow. Przyklad: java FuzzyExample string<plik_fcl> int<poziom natezenia> int<pora dnia>");
        } catch (NumberFormatException ex) {
            System.out
                    .println("Niepoprawny parametr. Przyklad: java FuzzyExample string<plik_fcl> int<poziom natezenia> int<pora dnia>");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    public void fuzzyMethod(){ // show charts
        try {
            String fileName = "src/main/resources/rules.fcl";
            FIS fis = FIS.load(fileName, false);
            JFuzzyChart.get().chart(fis);
            setVars(300, 1,60);
            fis.evaluate();
            Variable v = fis.getVariable("deltaVX");

        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out
                    .println("Niepoprawna liczba parametrow. Przyklad: java FuzzyExample string<plik_fcl> int<poziom natezenia> int<pora dnia>");
        } catch (NumberFormatException ex) {
            System.out
                    .println("Niepoprawny parametr. Przyklad: java FuzzyExample string<plik_fcl> int<poziom natezenia> int<pora dnia>");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
