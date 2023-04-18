import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SpamMonitor {
    public SpamMonitor() {}

    public Map<String, Double> emailSetSpamProbability(LinkedHashMap<String, String> emails) {
        Map<String, Double> probabilities = new HashMap<>();
        List<String> tempMails = new ArrayList<>(emails.values());

        for (int index = 0; index < emails.size(); index++) {
            String emailId = emails.keySet().toArray()[index].toString();
            String emailBody = emails.values().toArray()[index].toString();

            tempMails.remove(emailBody);

            double probability = spamProbability(emailBody, tempMails);
            probabilities.put(emailId, probability);

            tempMails.add(emailBody);
        }

        return probabilities;
    }

    private double spamProbability(String referenceEmail, List<String> emails) {
        double accProbability = 0.0d;

        for(String email : emails) {
            accProbability += cosineSimilarity(referenceEmail, email);
        }

        return (double) accProbability/ (double) emails.size();
    }

    private double cosineSimilarity(String str1, String str2) {
        String[] list1 = str1.split(" ");
        String[] list2 = str2.split(" ");

        Map<String,Long> count1 = Arrays.stream(list1).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<String,Long> count2 = Arrays.stream(list2).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));


        Set<String> intersection = getIntersection(count1, count2);
        double productOfVectors = productOfVectors(count1, count2, intersection);

        double d1 = 0.0d;
        for (final Long value : count1.values()) {
            d1 += value * value;
        }
        double d2 = 0.0d;
        for (final Long value : count2.values()) {
            d2 += value * value;
        }
        double cosineSimilarity;
        if (d1 <= 0.0 || d2 <= 0.0) {
            cosineSimilarity = 0.0;
        } else {
            cosineSimilarity = (productOfVectors / (Math.sqrt(d1) * Math.sqrt(d2)));
        }

        return cosineSimilarity;
    }

    private Set<String> getIntersection(
            Map<String, Long> vector1,
            Map<String, Long> vector2
    ) {
        Set<String> intersection = new HashSet<String>(vector1.keySet());
        intersection.retainAll(vector2.keySet());
        return intersection;
    }

    private long productOfVectors(
            Map<String, Long> vector1,
            Map<String, Long> vector2,
            Set<String> intersection
    ) {
        long product = 0;

        for(String key : intersection) {
            product += vector1.get(key) * vector2.get(key);
        }

        return product;
    }
}
