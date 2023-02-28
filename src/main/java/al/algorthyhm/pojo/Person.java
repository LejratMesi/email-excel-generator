package al.algorthyhm.pojo;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String emri;
    private String mbiemri;

    Providers providers;


    public List<String> generateRandomEmails(){
        List<String> emailCombinations = new ArrayList<>();

        emailCombinations.add(emri.toLowerCase().charAt(0)+ "." + mbiemri.toLowerCase() + "@" + providers.getProvider());
        emailCombinations.add(emri.toLowerCase() + "." + mbiemri.toLowerCase() + "@" +  providers.getProvider());
        emailCombinations.add(emri.toLowerCase() + mbiemri.toLowerCase() + "@"+ providers.getProvider());
        emailCombinations.add(emri.charAt(0)+ mbiemri.toLowerCase() + "@"+ providers.getProvider());
        emailCombinations.add(mbiemri.toLowerCase() + "." + emri.toLowerCase() + "@"+ providers.getProvider());
        emailCombinations.add(mbiemri.toLowerCase() + emri.toLowerCase() + "@"+ providers.getProvider());
        emailCombinations.add(emri.charAt(0)+ "." + mbiemri + "@" + providers.getProvider());
        emailCombinations.add(emri + "." + mbiemri + "@" +  providers.getProvider());
        emailCombinations.add(emri + mbiemri + "@"+ providers.getProvider());
        emailCombinations.add(emri.charAt(0)+ mbiemri + "@"+ providers.getProvider());
        emailCombinations.add(mbiemri + "." + emri + "@"+ providers.getProvider());
        emailCombinations.add(mbiemri + emri + "@"+ providers.getProvider());

        return emailCombinations;
    }


    public Providers getProviders() {
        return providers;
    }

    public void setProviders(Providers providers) {
        this.providers = providers;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }
}
