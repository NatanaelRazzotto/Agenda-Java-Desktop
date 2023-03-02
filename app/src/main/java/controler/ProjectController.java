
package controler;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

/**
 *
 * @author Natanael
 */
public class ProjectController {
     public void save(Project project) {
        String sql = "INSERT INTO projects (NAME,DESCRIPTION,CREATEDAT,UPDATEDAT) VALUES (?,?,?,?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
                        System.out.println(project);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdateAt().getTime()));

            statement.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar project " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);

        }

    }

    public void update(Project project) {
        String sql = "UPDATE projects SET IDPROJECT = ? ,NAME = ?, "
                + "DESCRIPTION = ?,CREATEDAT= ?,UPDATEDAT=? ,WHERE ID = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdateAt().getTime()));
            statement.setInt(5, project.getId());
            statement.execute();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar projects " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void removeById(int ProjectId) throws SQLException {
        String sql = "DELETE FROM projects WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, ProjectId);
            statement.execute();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar a projects" + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public List<Project> getAll() {
        String sql = "SELECT * FROM projects ";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<Project> tasks = new ArrayList<Project>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Project task = new Project();
                task.setId(resultSet.getInt("id"));              
                task.setName(resultSet.getString("NAME"));
                task.setDescription(resultSet.getString("DESCRIPTION"));
                task.setCreatedAt(resultSet.getDate("CREATEDAT"));
                task.setUpdateAt(resultSet.getDate("UPDATEDAT"));
                tasks.add(task);
                
            }
       } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar a projects" + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement,resultSet);
        }
        return tasks;
    }
}
