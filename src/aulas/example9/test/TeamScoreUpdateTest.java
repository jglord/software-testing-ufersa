package aulas.example9.test;
// ----------------------------------------------------------------------
// IMPORTS
// ----------------------------------------------------------------------
import aulas.example8.main.SAPException;
import aulas.example9.main.DatabaseException;
import aulas.example9.main.IDatabase;
import aulas.example9.main.TeamScoreUpdate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.params.provider.CsvSource;
import static org.mockito.Mockito.*;

// ----------------------------------------------------------------------
// Test class
// ----------------------------------------------------------------------
public class TeamScoreUpdateTest {
    private IDatabase db = mock(IDatabase.class);
    private TeamScoreUpdate ts = new TeamScoreUpdate(db);
    private int[] emptyScores = new int[10];
    private int[] oneScore = {1};
    private int[] manyScores = {1, 2, 3};

    @ParameterizedTest
    @CsvSource({"Time 1", "Time 2", "Time 3"})
    void verifyInputs(String teamId) {
        ts.calculateTotalAndStore(teamId, emptyScores);     // T1 -> Verifica a saída esperada para um array com nenhuma pontuação.
        ts.calculateTotalAndStore(teamId, oneScore);        // T2 -> Verifica a saída esperada para um array com uma única pontuação.
        ts.calculateTotalAndStore(teamId, manyScores);      // T3 -> Verifica a saída esperada para um array com múltiplas pontuações.

        // T4 -> Verifica as interações com a interface IDatabase, para garantir que cada equipe tenha a pontuação total atualizada uma vez.
        verify(db, times(1)).updateScores(teamId, 1);
    }
    @Test
    void verifyException() {
        // T5 -> Verifica se, em caso de alguma falha de conexão ao banco de dados, uma exceção é lançada.
        ts.calculateTotalAndStore("Time 1", manyScores);
        doThrow(new DatabaseException()).when(db).updateScores("Time 1", 5);
    }
}