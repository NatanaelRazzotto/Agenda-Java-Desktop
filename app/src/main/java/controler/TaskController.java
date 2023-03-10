package controler;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

/**
 * @author Natanael
 */
public class TaskController {

    public void save(Task task) {
        String sql = "INSERT INTO tasks (IDPROJECT,NAME,DESCRIPTION,COMPLETED,"
                + "NOTES,DEADLINE,CREATEDAT,UPDATEDAT) VALUES (?,?,?,?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            System.out.println(task);
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdateAt().getTime()));

            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar tarefa " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);

        }

    }

    public void update(Task task) {
        String sql = "UPDATE tasks SET IDPROJECT = ? ,NAME = ?, "
                + "DESCRIPTION = ?,COMPLETED = ?, NOTES= ?,DEADLINE= ?,"
                + "CREATEDAT= ?,UPDATEDAT=? WHERE ID = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdateAt().getTime()));
            statement.setInt(9, task.getId());
            statement.execute();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar tarefa " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void removeById(int taskId){
        String sql = "DELETE FROM tasks WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, taskId);
            statement.execute();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar a tarefa" + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public List<Task> getAll(int idProject) {
        String sql = "SELECT * FROM tasks WHERE IDPROJECT = ? ";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<Task> tasks = new ArrayList<Task>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idProject);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("IDPROJECT"));
                task.setName(resultSet.getString("NAME"));
                task.setDescription(resultSet.getString("DESCRIPTION"));
                task.setNotes(resultSet.getString("NOTES"));
                task.setIsCompleted(resultSet.getBoolean("COMPLETED"));
                task.setDeadline(resultSet.getDate("DEADLINE"));
                task.setCreatedAt(resultSet.getDate("CREATEDAT"));
                task.setUpdateAt(resultSet.getDate("UPDATEDAT"));
                tasks.add(task);
                
            }
       } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar a tarefa" + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement,resultSet);
        }
        return tasks;
    }
}
