package aulas.example9.main;

public class TeamScoreUpdate {
    public IDatabase databaseImpl;

    public TeamScoreUpdate(IDatabase databaseImpl){
        this.databaseImpl = databaseImpl;
    }

    public void calculateTotalAndStore(String teamId, int[] scores){
        int total = 0;
        for(int score : scores)
            total = total + score;

        // storing total in the database
        databaseImpl.updateScores(teamId, total);
    }
}
