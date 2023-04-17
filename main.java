import org.apache.commons.text.similarity.CosineSimilarity;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class JavaTest {
    public static void main(String[] args) {
        String email1 = """
            Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
            Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, 
            when an unknown printer took a galley of type and scrambled it to make a type specimen book. 
            It has survived not only five centuries, but also the leap into electronic typesetting, 
            remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset 
            sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like 
            Aldus PageMaker including versions of Lorem Ipsum.    
        """;

        String email2 = """
            Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
            Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, 
            when an unknown printer took a galley of type and scrambled it to make a type specimen book. 
            It has survived not only five centuries, but also the leap into electronic typesetting, 
            remaining essentially unchanged. Many desktop publishing packages and web page editors now use 
            Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites 
            still in their infancy. Various versions have evolved over the years, sometimes by accident, 
            sometimes on purpose (injected humour and the like)..    
        """;

        String email3 = """
            O Lorem Ipsum é um texto modelo da indústria tipográfica e de impressão. 
            O Lorem Ipsum tem vindo a ser o texto padrão usado por estas indústrias desde o ano de 1500, 
            quando uma misturou os caracteres de um texto para criar um espécime de livro. 
            Este texto não só sobreviveu 5 séculos, mas também o salto para a tipografia electrónica, 
            mantendo-se essencialmente inalterada. Foi popularizada nos anos 60 com a disponibilização das 
            folhas de Letraset, que continham passagens com Lorem Ipsum, e mais recentemente com os programas 
            de publicação como o Aldus PageMaker que incluem versões do Lorem Ipsum.
        """;

        System.out.println(cosineSimilarity(email1, email2));
        System.out.println(cosineSimilarity(email1, email3));

        CosineSimilarity documentsSimilarity = new CosineSimilarity();

        Map<CharSequence, Integer> vector1 = Arrays.stream(email1.split(" ")).collect(Collectors.toMap( character -> character, character -> 1, Integer::sum));
        Map<CharSequence, Integer> vector2 = Arrays.stream(email2.split(" ")).collect(Collectors.toMap( character -> character, character -> 1, Integer::sum));
        Map<CharSequence, Integer> vector3 = Arrays.stream(email3.split(" ")).collect(Collectors.toMap( character -> character, character -> 1, Integer::sum));

        double docABCosSimilarity = documentsSimilarity.cosineSimilarity(vector1, vector2);
        double docACCosSimilarity = documentsSimilarity.cosineSimilarity(vector1, vector3);
        System.out.printf("%4.3f\n", docABCosSimilarity);
        System.out.printf("%4.3f\n", docACCosSimilarity);
    }

    private static double cosineSimilarity(String str1, String str2) {
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

    private static Set<String> getIntersection(
            Map<String, Long> vector1,
            Map<String, Long> vector2
    ) {
        Set<String> intersection = new HashSet<String>(vector1.keySet());
        intersection.retainAll(vector2.keySet());
        return intersection;
    }

    private static long productOfVectors(
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