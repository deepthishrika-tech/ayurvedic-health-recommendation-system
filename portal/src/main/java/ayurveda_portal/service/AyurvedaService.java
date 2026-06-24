package ayurveda_portal.service;

import ayurveda_portal.model.AyurvedaRecord;
import com.opencsv.CSVReader;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class AyurvedaService {

    private List<AyurvedaRecord> records = new ArrayList<>();

    public List<AyurvedaRecord> getAllRecords() {
        return records;
    }

    public List<AyurvedaRecord> search(
            String keyword,
            String dosha,
            String bodySystem,
            List<String> symptoms) {

        List<ScoredRecord> scoredResults = new ArrayList<>();

        for (AyurvedaRecord record : records) {

            int score = 0;

            // Disease match
            if (record.getProblem() != null &&
                    record.getProblem().toLowerCase()
                            .contains(keyword.toLowerCase())) {

                score += 100;
            }

            // Symptoms match
            if (record.getSymptoms() != null &&
                    record.getSymptoms().toLowerCase()
                            .contains(keyword.toLowerCase())) {

                score += 80;
            }

            // Dosha match
            if (dosha != null &&
                    !dosha.isEmpty() &&
                    record.getDoshaType() != null &&
                    record.getDoshaType().toLowerCase()
                            .contains(dosha.toLowerCase())) {

                score += 20;
            }

            // Body system match
            if (bodySystem != null &&
                    !bodySystem.isEmpty() &&
                    record.getBodySystem() != null &&
                    record.getBodySystem().toLowerCase()
                            .contains(bodySystem.toLowerCase())) {

                score += 20;
            }

            // Multiple symptoms match
            if (symptoms != null &&
                    record.getSymptoms() != null) {

                for (String symptom : symptoms) {

                    if (record.getSymptoms()
                            .toLowerCase()
                            .contains(symptom.toLowerCase())) {

                        score += 10;
                    }
                }
            }

            // Add all disease-related results
            if (score > 0) {
                scoredResults.add(
                        new ScoredRecord(record, score)
                );
            }
        }

        scoredResults.sort(
                Comparator.comparingInt(
                        ScoredRecord::getScore)
                        .reversed()
        );

        List<AyurvedaRecord> finalResults =
                new ArrayList<>();

        int limit = Math.min(5, scoredResults.size());

        for (int i = 0; i < limit; i++) {

            finalResults.add(
                    scoredResults.get(i).getRecord()
            );
        }

        return finalResults;
    }

    private static class ScoredRecord {

        private AyurvedaRecord record;
        private int score;

        public ScoredRecord(
                AyurvedaRecord record,
                int score) {

            this.record = record;
            this.score = score;
        }

        public AyurvedaRecord getRecord() {
            return record;
        }

        public int getScore() {
            return score;
        }
    }

    @PostConstruct
    public void loadData() {

        try {

            ClassPathResource resource =
                    new ClassPathResource(
                            "data/ayurveda_dataset.csv");

            try (CSVReader reader =
                         new CSVReader(
                                 new InputStreamReader(
                                         resource.getInputStream()))) {

                List<String[]> rows =
                        reader.readAll();

                for (int i = 1; i < rows.size(); i++) {

                    String[] row = rows.get(i);

                    AyurvedaRecord record =
                            new AyurvedaRecord();

                    record.setProblem(row[1]);
                    record.setSymptoms(row[2]);
                    record.setRemedies(row[3]);
                    record.setMedicines(row[4]);
                    record.setDoshaType(row[6]);
                    record.setBodySystem(row[7]);
                    record.setTreatmentType(row[9]);
                    record.setContraindications(row[10]);
                    record.setPreventiveAdvice(row[11]);
                    record.setSeasonalSuitability(row[12]);
                    record.setConfidence(row[14]);

                    records.add(record);
                }
            }

            System.out.println(
                    "Total recommendations loaded: "
                            + records.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}