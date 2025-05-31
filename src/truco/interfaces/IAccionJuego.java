package truco.interfaces;

public interface IAccionJuego {
    void cantarTruco();
    void cantarReTruco();
    void cantarValeCuatro();
    void cantarEnvido();
    void cantarRealEnvido();
    void cantarFaltaEnvido();

    void aceptar();
    void rechazar();
}