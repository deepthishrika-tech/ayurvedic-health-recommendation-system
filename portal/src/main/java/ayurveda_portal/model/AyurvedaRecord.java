package ayurveda_portal.model;

public class AyurvedaRecord {

    private String problem;
    private String symptoms;
    private String remedies;
    private String medicines;
    private String doshaType;
    private String bodySystem;
    private String treatmentType;
    private String contraindications;
    private String preventiveAdvice;
    private String seasonalSuitability;
    private String confidence;

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getRemedies() {
        return remedies;
    }

    public void setRemedies(String remedies) {
        this.remedies = remedies;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public String getDoshaType() {
        return doshaType;
    }

    public void setDoshaType(String doshaType) {
        this.doshaType = doshaType;
    }

    public String getBodySystem() {
        return bodySystem;
    }

    public void setBodySystem(String bodySystem) {
        this.bodySystem = bodySystem;
    }

    public String getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }

    public String getContraindications() {
        return contraindications;
    }

    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }

    public String getPreventiveAdvice() {
        return preventiveAdvice;
    }

    public void setPreventiveAdvice(String preventiveAdvice) {
        this.preventiveAdvice = preventiveAdvice;
    }

    public String getSeasonalSuitability() {
        return seasonalSuitability;
    }

    public void setSeasonalSuitability(String seasonalSuitability) {
        this.seasonalSuitability = seasonalSuitability;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }
}