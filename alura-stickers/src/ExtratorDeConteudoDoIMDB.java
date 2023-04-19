import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDoIMDB implements ExtratorDeConteudo{
    
    public List<Conteudo> extraiConteudos(String json){
        JsonParser parser = new JsonParser();                
        List<Map<String, String>> ListaDeAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        //popular a lista de conteudos
        for (Map<String, String> atributos : ListaDeAtributos) {
            String titulo = atributos.get("title");    
            String urlImagem = atributos.get("image").replaceAll("(@+)(.*).jpg$",  "$1.jpg");
            var conteudo = new Conteudo(titulo, urlImagem);

            conteudos.add(conteudo);
        }
        return conteudos;
    }
    
    
}
