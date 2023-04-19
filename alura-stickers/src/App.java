import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    private static List<Conteudo> extraiConteudos;

    public static void main(String[] args) throws Exception {
        //1-Pegar os dados da IMBD - fazer uma conexão HHTP e buscar os top 250 filmes
        
        //• API IMDB        
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
        
        //• API NASA
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        //2-Pegar/extrair/parsear só os dados necessários (Titulo, imagem e classificação)
        
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        //3-Exibir e Manipular os dados na aplicação
        var diretorio = new File("saida/");
        diretorio.mkdir();

        var geradora = new GeradoraDeFigurinhas();
        for (int i = 0; i < 3; i++) {
            
            Conteudo conteudo = conteudos.get(i);
            
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();        
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";
            
            geradora.cria(inputStream, nomeArquivo);
            
            System.out.println(conteudo.getTitulo());
            System.out.println();            
        }

    }
}
