# Java Test
Welcome to my Java Test, here you can found a (re)implementation of the mathematical model Cosine Similarity. In java, 
we already have this implemented in Apache Commons Text utility lib, here I've reimplemented it and used the original
lib to compare the results.

## The mathematical model
Cosine Similarity measures the similarity between two vectors of an inner product space. It is measured by the cosine of 
the angle between two vectors and determines whether two vectors are pointing in roughly the same direction. 
It is often used to measure document similarity in text analysis. (HAN, J.; KAMBER, M. **Data Mining: Concepts and Techniques.**)

Here we have the formula of this equation  
![Cosine Similarity Formula](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSaEEPQk9z7Fu1ij0WBFJ0HUQWQNfBvCM87VG4F64j0&s)

I've opted by using the Cosine Similarity instead of the Euclidean model because even if the two similar documents have
different sizes, the Euclidean model would consider they with small similarity but with Cosine model, if a word appears
multiple times, the angle could still be lower and a lower angle means a high similarity.

## The Code

### productOfVectors
This function does the calculation of the product between two Maps, these Maps are filled with the words of each email 
grouped by the word and the count of time that this word appears. The intersection is a Set containing the words
present in both Maps, using the intersections, we can ignore the zero values as they don't aggregate in our calculation
```java
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
```

### getIntersection
This function just get the intersection between two Maps, these maps contains the words and the count of times that it 
appears. It uses the HasSet retainAll function, this function will take off all words that doesn't belong to both maps,
it receives the vector2 keySet to be compared with vector1 keySet.
```java
private static Set<String> getIntersection(
            Map<String, Long> vector1,
            Map<String, Long> vector2
    ) {
        Set<String> intersection = new HashSet<String>(vector1.keySet());
        intersection.retainAll(vector2.keySet());
        return intersection;
    }
```

### cosineSimilarity
Here we have the core of our application, this function is responsible to calculate the similarity between two texts, it
receives two strings, in our case, two emails body, then it splits the string using the space as delimiter, then it
uses the Array Stream to count and group the words in a Map using the word as Key and the count as value. We do it for
both strings.

Then, we extract the intersection of both maps to use later into **productOfVectors** function, after it, we already can 
calculate the product of both vectors.

Following, we calculate the square of each word count and sum it. We do it to both vectors.

By the end, we finally apply the model formula, divides the product of vectors by multiplication of the square root of the
sum of squares of each value of vectors.
```java
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
```

## main
The main function contains three sample emails using Lorem Ipsum texts, it calls the cosineSimilarity function comparing
email1 with email2 and then email1 with email3. It checks using the reimplementation and the original Apache implementation
of Cosine Similarity

```java
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
```
