package tresenraya;

import java.awt.Color;

public class LogicaJuego {
    int turno, pX, pO;
    boolean habilitado; // Habilita y deshabilita el tablero

    /**
     * Inicializaremos el juego con las siguientes variables_
     * @param turno (Nos indicará el turno del jugador: 0 - X, 1 - O)
     * @param pX (Contiene el valor total de las victorias de este jugador)
     * @param pO (Contiene el valor total de las victorias de este jugador)
     */
    public LogicaJuego(int turno, int pX, int pO) {
        this.turno = turno;
        this.pX = pX;
        this.pO = pO;
    }

    /**
     * Obtener turno
     * @return 
     */
    public int getTurno() {
        return turno;
    }

    /**
     * Insertar turno
     * @param turno 
     */
    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getpX() {
        return pX;
    }

    public void setpX(int pX) {
        this.pX = pX;
    }

    public int getpO() {
        return pO;
    }

    public void setpO(int pO) {
        this.pO = pO;
    }

    public void cambioTurno(){

        if (getTurno() == 0) setTurno(1);
        else setTurno(0);
    }
    
    /**
     * Comprobar si se ha conseguido un tres en raya, 
     * en caso que se haya conseguido devolverá 1, en caso contrario retorna 0 y continúa el juego
     * @param matriz (Tablero de juego)
     * @return 
     */
    public int comprobarJuego(int matriz[][]){
        
        // Comprobar horizontal
        if((matriz[0][0]==matriz[0][1])&&(matriz[0][1]==matriz[0][2])) return 1;
        if((matriz[1][0]==matriz[1][1])&&(matriz[1][1]==matriz[1][2])) return 1;
        if((matriz[2][0]==matriz[2][1])&&(matriz[2][1]==matriz[2][2])) return 1;
        
        //Comprobar vertical
        if((matriz[0][0]==matriz[1][0])&&(matriz[1][0]==matriz[2][0])) return 1;
        if((matriz[0][1]==matriz[1][1])&&(matriz[1][1]==matriz[2][1])) return 1;
        if((matriz[0][2]==matriz[1][2])&&(matriz[1][2]==matriz[2][2])) return 1;
        
        //Comprobar en diagonal
        if((matriz[0][0]==matriz[1][1])&&(matriz[1][1]==matriz[2][2])) return 1;
        if((matriz[0][2]==matriz[1][1])&&(matriz[1][1]==matriz[2][0])) return 1;
        
        // Si no hay tres en raya
        return 0;
    }
    
    /**
     * Deshabilitará el botón para evitar que se vuelva a posicionar una ficha en ese hueco
     * @param bt (Botón seleccionado)
     * @param x (Posición x en el tablero)
     * @param y (Posición y en el tablero)
     * @param matriz (Tablero de juego)
     * @param jp (Panel dónde se sitúa el tablero de juego)
     * @param lX (JLabel del jugador X)
     * @param lO (JLabel del jugador O)
     * @return 
     */
    public int tiradaJugador(javax.swing.JButton bt, int x, int y, int matriz[][], javax.swing.JPanel jp, javax.swing.JLabel lX, javax.swing.JLabel lO){

        bt.setEnabled(false);

        ponerFicha(matriz, x, y, bt);
               
        // Comprobar si se ha ganado la partida
        if(comprobarJuego(matriz)==1){
            ganador(lX, lO);
            habilitado = false;
            habilitarTablero(jp);
            
        } else cambioTurno();
         
         // Deshabilitar tablero
         return 0;
    }
    
    /**
     * Actualizar la puntuación de cada jugador al ganar la partida
     * Al finalizar el incremento de puntuación es necesario cambiar de turno
     * @param lX (JLabel del jugador X)
     * @param lO (JLabel del jugador O)
     */
    public void ganador(javax.swing.JLabel lX, javax.swing.JLabel lO){
        
        if(getTurno()==0){
            pX++;
            lX.setText(String.valueOf(pX));
        } else{
            pO++;
            lO.setText(String.valueOf(pO));
        }
        cambioTurno();
    }
    
    /**
     * Habilitará o deshabilitará el tablero dependiendo del estado de la variable habilitado
     * @param jp  (Panel dónde se sitúa el tablero de juego)
     */
    public void habilitarTablero( javax.swing.JPanel jp){

        for(int i=0; i<jp.getComponents().length; i++) jp.getComponent(i).setEnabled(habilitado); //si habilitado=false deshabilita
    }
    
    /**
     * Insertaremos la ficha en la posición correspondiente de la matriz
     * Llamaremos al método pintarFicha
     * @param matriz (Tablero de juego)
     * @param t (Turno)
     * @param x (Posición x en el tablero)
     * @param y (Posición y en el tablero)
     * @param bt (Botón pulsado)
     */
    public void ponerFicha(int matriz[][], int x, int y, javax.swing.JButton bt){

        matriz[x][y] = getTurno();
        
        pintarFicha(bt);        
    }
    
    /**
     * Pintará la ficha en el tablero de juego visual, es decir, en el botón
     * @param bt (Botón pulsado)
     */
    private void pintarFicha(javax.swing.JButton bt){
        
        if(getTurno() == 0){
            
            bt.setForeground(Color.red);
            bt.setText("X");
        } else {
            
            bt.setForeground(Color.blue);
            bt.setText("O");
        }
    }
    
    /**
     * Inicializa una nueva partida, reinicia la matriz (Tablero de juego) y habilita el tablero
     * 
     * @param matriz (Tablero de juego)
     * @param jp (Panel dónde se sitúa el tablero de juego)
     */
    public void iniciarPartida(int matriz[][], javax.swing.JPanel jp){
        for (int x = 0; x < 3; x++)
            for (int y = 0; y < 3; y++) 
                matriz[x][y]=(x+10)*(y+10);

         habilitado = true;
         habilitarTablero(jp);
    }
}