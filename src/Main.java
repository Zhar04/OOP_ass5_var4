import java.util.ArrayList;
import java.util.List;

class Library {
    private String name;
    private List<Department> departments;

    public Library(String name) {
        this.name = name;
        this.departments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public void removeDepartment(Department department) {
        departments.remove(department);
    }

    public List<Edition> searchPublicationsByYear(int year) {
        List<Edition> foundPublications = new ArrayList<>();

        for (Department department : departments) {
            for (Edition edition : department.getEditions()) {
                if (edition.getYearOfPublication() == year) {
                    foundPublications.add(edition);
                }
            }
        }

        return foundPublications;
    }
}

class Department {
    private String genreName;
    private Library library;
    private List<Edition> editions;

    public Department(String genreName) {
        this.genreName = genreName;
        this.editions = new ArrayList<>();
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public List<Edition> getEditions() {
        return editions;
    }

    public void addEdition(Edition edition) {
        editions.add(edition);
        edition.setDepartment(this);
    }

    public void removeEdition(Edition edition) {
        editions.remove(edition);
        edition.setDepartment(null);
    }

    public int getNumberOfEditions() {
        return editions.size();
    }
}

class Edition {
    private String name;
    private String author;
    private int yearOfPublication;
    private Department department;

    public Edition(String name, String author, int yearOfPublication) {
        this.name = name;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}

public class Main {
    public static void main(String[] args) {
        // Part a
        Library myLibrary = new Library("City Library");
        Department fictionDepartment = new Department("Fiction");
        Department scienceDepartment = new Department("Science");

        myLibrary.addDepartment(fictionDepartment);
        myLibrary.addDepartment(scienceDepartment);

        Edition edition1 = new Edition("Book1", "Author1", 2000);
        Edition edition2 = new Edition("Book2", "Author2", 2010);
        Edition edition3 = new Edition("Book3", "Author3", 2020);

        fictionDepartment.addEdition(edition1);
        fictionDepartment.addEdition(edition2);
        scienceDepartment.addEdition(edition3);

        System.out.println("Library Name: " + myLibrary.getName());
        for (Department department : myLibrary.getDepartments()) {
            System.out.println("Department Genre: " + department.getGenreName());
            System.out.println("Number of Editions: " + department.getNumberOfEditions());
            for (Edition edition : department.getEditions()) {
                System.out.println("Edition: " + edition.getName() +
                        ", Author: " + edition.getAuthor() +
                        ", Year of Publication: " + edition.getYearOfPublication());
            }
            System.out.println();
        }

        // Part b
        myLibrary.removeDepartment(scienceDepartment);

        System.out.println("Library Name: " + myLibrary.getName());
        for (Department department : myLibrary.getDepartments()) {
            System.out.println("Department Genre: " + department.getGenreName());
            System.out.println("Number of Editions: " + department.getNumberOfEditions());
            for (Edition edition : department.getEditions()) {
                System.out.println("Edition: " + edition.getName() +
                        ", Author: " + edition.getAuthor() +
                        ", Year of Publication: " + edition.getYearOfPublication());
            }
            System.out.println();
        }

        // Part c
        int searchYear = 2010;
        List<Edition> foundPublications = myLibrary.searchPublicationsByYear(searchYear);

        System.out.println("Publications released in " + searchYear + ":");
        for (Edition edition : foundPublications) {
            System.out.println("Edition: " + edition.getName() +
                    ", Author: " + edition.getAuthor() +
                    ", Year of Publication: " + edition.getYearOfPublication() +
                    ", Department: " + edition.getDepartment().getGenreName());
        }
    }
}
