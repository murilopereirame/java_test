import java.util.*;

class JavaTest {
    public static void main(String[] args) {
        LinkedHashMap<String, String> emails = new LinkedHashMap<>();
        emails.put("e4e1ba33-730b-429f-86c2-763a48610a8b", """
            Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
            Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, 
            when an unknown printer took a galley of type and scrambled it to make a type specimen book. 
            It has survived not only five centuries, but also the leap into electronic typesetting, 
            remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset 
            sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like 
            Aldus PageMaker including versions of Lorem Ipsum.    
        """);

        emails.put("ef896d99-017c-4e94-9690-12fecb826010", """
            O Lorem Ipsum é um texto modelo da indústria tipográfica e de impressão. 
            O Lorem Ipsum tem vindo a ser o texto padrão usado por estas indústrias desde o ano de 1500, 
            quando uma misturou os caracteres de um texto para criar um espécime de livro. 
            Este texto não só sobreviveu 5 séculos, mas também o salto para a tipografia electrónica, 
            mantendo-se essencialmente inalterada. Foi popularizada nos anos 60 com a disponibilização das 
            folhas de Letraset, que continham passagens com Lorem Ipsum, e mais recentemente com os programas 
            de publicação como o Aldus PageMaker que incluem versões do Lorem Ipsum.
        """);

        emails.put("9f8e1ddd-336c-46d7-b64d-f2e804b696ae", """
            Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
            Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, 
            when an unknown printer took a galley of type and scrambled it to make a type specimen book. 
            It has survived not only five centuries, but also the leap into electronic typesetting, 
            remaining essentially unchanged. Many desktop publishing packages and web page editors now use 
            Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites 
            still in their infancy. Various versions have evolved over the years, sometimes by accident, 
            sometimes on purpose (injected humour and the like)..    
        """);

        emails.put("d11d3253-be32-4c29-a3c3-7a711e0ab8ee", """
            Hay muchas variaciones de los pasajes de Lorem Ipsum disponibles, pero la mayoría sufrió alteraciones en 
            alguna manera, ya sea porque se le agregó humor, o palabras aleatorias que no parecen ni un poco creíbles. 
            Si vas a utilizar un pasaje de Lorem Ipsum, necesitás estar seguro de que no hay nada avergonzante escondido 
            en el medio del texto. Todos los generadores de Lorem Ipsum que se encuentran en Internet tienden a 
            repetir trozos predefinidos cuando sea necesario, haciendo a este el único generador verdadero (válido) en 
            la Internet. Usa un diccionario de mas de 200 palabras provenientes del latín, combinadas con estructuras 
            muy útiles de sentencias, para generar texto de Lorem Ipsum que parezca razonable. Este Lorem Ipsum generado 
            siempre estará libre de repeticiones, humor agregado o palabras no características del lenguaje, etc.        
        """);

        emails.put("fb15935d-0284-43fc-948e-c43680befe79", """
            The lion is a large cat of the genus Panthera native to Africa and India. 
            It has a muscular, broad-chested body; short, rounded head; round ears; and a hairy tuft at the end of its tail. 
            It is sexually dimorphic; adult male lions are larger than females and have a prominent mane.
       """);

        emails.put("402140cd-8010-46ff-964f-3c6912c9f74b", """
            Lo scopo dell’utilizzo del Lorem Ipsum è che offre una normale distribuzione delle lettere (al contrario di 
            quanto avviene se si utilizzano brevi frasi ripetute, ad esempio “testo qui”), apparendo come un normale 
            blocco di testo leggibile. Molti software di impaginazione e di web design utilizzano Lorem Ipsum come 
            testo modello. Molte versioni del testo sono state prodotte negli anni, a volte casualmente, a volte di 
            proposito (ad esempio inserendo passaggi ironici).
        """);
        emails.put("35f37eed-1026-4608-86fa-9cb45a99c11b", """
            Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
            Lorem Ipsum has been the industry's standard dummy text ever since the 1250s, 
            when an unknown printer took a galley of type and scrambled it to make a book. 
            It has survived not only five centuries, but also the leap into electronic typesetting, 
            remaining essentially unchanged. In the 1930s it was popularised with the release of Letraset 
            sheets containing Lorem Ipsum passages, and more recently with publishing software like 
            Aldus PageMaker including versions of it.
        """);

        emails.put("2208df99-9ab7-41b8-a30e-c8a5a8a3513c", """
            Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
            Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, 
            when an unknown printer took a galley of type and scrambled it to make a type specimen book. 
            It has survived not only five centuries, but also the leap into electronic typesetting, 
            remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset 
            sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like 
            Aldus PageMaker including versions of Lorem Ipsum.    
        """);

        SpamMonitor monitor = new SpamMonitor();
        monitor.emailSetSpamProbability(emails).forEach((key, value) -> System.out.println("Email Id: " + key + " - Spam Probability: " + value));
    }


}