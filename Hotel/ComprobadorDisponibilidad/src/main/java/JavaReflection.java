
public class JavaReflection {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        Class<?> clase = Class.forName("com.springsample.dto.UsuarioDto");
                Object dto = clase.newInstance();
                System.out.println(dto);
    }
}
