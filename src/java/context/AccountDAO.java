package context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Bill;
import model.BillDetail;
import model.Books;
import model.Category;
import model.Contact;

public class AccountDAO extends DBContext {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public Account getAccount(String userName, String password) throws Exception {
        Account account = null;
        Account account1 = null;
        String sql = "SELECT * FROM Account WHERE account_name=? and account_pass=?";
        try {
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                int accountID = rs.getInt("account_id");
                String accountName = rs.getString("account_name");
                String accountEmail = rs.getString("account_email");
                String accountPass = rs.getString("account_pass");
                String accountPhone = rs.getString("account_phone");
                int isAdmin = rs.getInt("isAdmin");
                int status = rs.getInt("status");
                account = new Account(accountID, accountName, accountEmail, accountPass, accountPhone, isAdmin, status);
                //account1 = new Account(accountID, accountName, accountEmail, accountPass, accountPhone, isAdmin);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
        return account;
    }

    public Account getAccountByUsername(String userName) throws Exception {
        Account account = null;
        String sql = "SELECT * FROM Account WHERE account_name=?";
        try {
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            while (rs.next()) {
                int accountID = rs.getInt("account_id");
                String accountName = rs.getString("account_name");
                String accountEmail = rs.getString("account_email");
                String accountPass = rs.getString("account_pass");
                String accountPhone = rs.getString("account_phone");
                account = new Account(accountID, accountName, accountEmail, accountPass, accountPhone);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
        return account;

    }

    public Account getAccountByID(int accountID) throws Exception {
        Account account = null;
        String sql = "SELECT * FROM Account WHERE account_id=?";
        try {
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, accountID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int accountId = rs.getInt("account_id");
                String accountName = rs.getString("account_name");
                String accountEmail = rs.getString("account_email");
                String accountPass = rs.getString("account_pass");
                String accountPhone = rs.getString("account_phone");
                int isAdmin = rs.getInt("isAdmin");
                int Status = rs.getInt("Status");
                account = new Account(accountID, accountName, accountEmail, accountPass, accountPhone, isAdmin, Status);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
        return account;

    }

    public int addAccount(Account a) throws Exception {
        int status = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement("insert into Account values(?,?,?,?,?,?)");
            ps.setString(1, a.getAccountName());
            ps.setString(2, a.getAccountEmail());
            ps.setString(3, a.getAccountPass());
            ps.setString(4, a.getAccountPhone());
            ps.setInt(5, 0);
            ps.setInt(6, 0);
            status = ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
        return status;
    }

    public ArrayList<Category> getListCategory() throws Exception {
        try {
            String sql = "SELECT * FROM category";
            con = getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<Category> list = new ArrayList<>();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryID(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                list.add(category);
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
    }

    public ArrayList<Books> getListBooksByCategory(String id) throws Exception {
        try {
            String sql = "SELECT * FROM Books where category_id = ?";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            ArrayList<Books> list = new ArrayList<>();
            while (rs.next()) {
                Books books = new Books();
                books.setBookID(rs.getString("book_id"));
                books.setCategoryID(rs.getInt("category_id"));
                books.setBookName(rs.getString("book_name"));
                books.setBookImage(rs.getString("book_image"));
                books.setBookPrice(rs.getLong("book_price"));
                books.setBookDescription(rs.getString("book_description"));
                list.add(books);
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
    }

    public ArrayList<Books> getListSearchBooks(String name) {
        try {
            String sql = "SELECT * FROM Books where book_name like '%" + name + "%'";
            con = getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<Books> list = new ArrayList<>();
            while (rs.next()) {
                Books books = new Books();
                books.setBookID(rs.getString("book_id"));
                books.setCategoryID(rs.getInt("category_id"));
                books.setBookName(rs.getString("book_name"));
                books.setBookImage(rs.getString("book_image"));
                books.setBookPrice(rs.getLong("book_price"));
                books.setBookDescription(rs.getString("book_description"));
                list.add(books);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        } 
        return null;
    }

    public ArrayList<Books> getBookByCateAndName(int catId, String nameBook)  {
        try {
            con = getConnection();
            
            String sql;
            if (catId == 0) {
                sql = "SELECT * FROM Books where book_name like ?";
                ps = con.prepareStatement(sql);
                ps.setString(2, "%"+nameBook +"%");
            } else {
                sql = "SELECT * FROM Books where category_id = ? and [book_name] like ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, catId);
                ps.setString(2, "%"+nameBook +"%");
            }        
                      
            rs = ps.executeQuery();
            ArrayList<Books> list = new ArrayList<>();
            while (rs.next()) {
                Books books = new Books();
                books.setBookID(rs.getString("book_id"));
                books.setCategoryID(rs.getInt("category_id"));
                books.setBookName(rs.getString("book_name"));
                books.setBookImage(rs.getString("book_image"));
                books.setBookPrice(rs.getLong("book_price"));
                books.setBookDescription(rs.getString("book_description"));
                list.add(books);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
            }

    public Books getBookById(String id) throws Exception {
        Books book = new Books();
        String sql = "SELECT * FROM Books WHERE book_id=?";
        try {
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String bookID = rs.getString("book_id");
                int categoryID = rs.getInt("category_id");
                String bookName = rs.getString("book_name");
                String bookImage = rs.getString("book_image");
                long bookPrice = rs.getLong("book_price");
                String bookDescription = rs.getString("book_description");
                book = new Books(bookID, categoryID, bookName, bookImage, bookPrice, bookDescription);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
        return book;
    }

    public ArrayList<Books> getTop6ListNewBook() throws Exception {
        try {
            String sql = "select top(6) * from Books order by book_id desc";
            con = getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<Books> list = new ArrayList<>();
            while (rs.next()) {
                Books books = new Books();
                books.setBookID(rs.getString("book_id"));
                books.setCategoryID(rs.getInt("category_id"));
                books.setBookName(rs.getString("book_name"));
                books.setBookImage(rs.getString("book_image"));
                books.setBookPrice(rs.getLong("book_price"));
                books.setBookDescription(rs.getString("book_description"));
                list.add(books);
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
    }

    public ArrayList<Books> getTop6ListBook(int categoryId) throws Exception {
        try {
            String sql = "select top(6) * from Books where category_id = ?";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, categoryId);
            rs = ps.executeQuery();
            ArrayList<Books> list = new ArrayList<>();
            while (rs.next()) {
                Books books = new Books();
                books.setBookID(rs.getString("book_id"));
                books.setCategoryID(rs.getInt("category_id"));
                books.setBookName(rs.getString("book_name"));
                books.setBookImage(rs.getString("book_image"));
                books.setBookPrice(rs.getLong("book_price"));
                books.setBookDescription(rs.getString("book_description"));
                list.add(books);
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
    }

    public int insertBillDetail(BillDetail bill) throws Exception {
        int status = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement("insert into BillDetails values(?,?,?,?,?,?)");
            ps.setString(1, bill.getBookID());
            ps.setInt(2, bill.getAccountID());
            ps.setString(3, bill.getBookName());
            ps.setString(4, bill.getBookImage());
            ps.setLong(5, bill.getPrice());
            ps.setInt(6, bill.getQuantity());
            status = ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
        return status;
    }

    public int deleteBillDetail(int billDetailId) throws Exception {
        int status = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement("delete from BillDetails where bill_detail_id = ?");
            ps.setInt(1, billDetailId);
            status = ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
        return status;
    }

    public int addQuantity(int billDetailId) throws Exception {
        int status = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement("update BillDetails set quantity = quantity+1 where bill_detail_id = ?");
            ps.setInt(1, billDetailId);
            status = ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
        return status;
    }

    public ArrayList<BillDetail> getListBillDetail(int accountId) throws Exception {
        try {
            String sql = "SELECT * FROM BillDetails where account_id = ?";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();
            ArrayList<BillDetail> list = new ArrayList<>();
            while (rs.next()) {
                BillDetail billDetail = new BillDetail();
                billDetail.setBillDetailID(rs.getInt("bill_detail_id"));
                billDetail.setBookID(rs.getString("book_id"));
                billDetail.setAccountID(rs.getInt("account_id"));
                billDetail.setBookName(rs.getString("book_name"));
                billDetail.setBookImage(rs.getString("book_image"));
                billDetail.setPrice(rs.getLong("price"));
                billDetail.setQuantity(rs.getInt("quantity"));
                list.add(billDetail);
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
    }

    public BillDetail getBillDetail(int billDetailId) throws Exception {
        try {
            String sql = "SELECT * FROM BillDetails where bill_detail_id = ?";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, billDetailId);
            rs = ps.executeQuery();
            BillDetail billDetail = new BillDetail();
            while (rs.next()) {
                billDetail.setBillDetailID(rs.getInt("bill_detail_id"));
                billDetail.setBookID(rs.getString("book_id"));
                billDetail.setAccountID(rs.getInt("account_id"));
                billDetail.setBookName(rs.getString("book_name"));
                billDetail.setBookImage(rs.getString("book_image"));
                billDetail.setPrice(rs.getLong("price"));
                billDetail.setQuantity(rs.getInt("quantity"));
            }
            return billDetail;
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
    }

    public int subQuantity(int billDetailId) throws Exception {
        int status = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement("update BillDetails set quantity = quantity-1 where bill_detail_id = ?");
            ps.setInt(1, billDetailId);
            status = ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
        return status;
    }

    public int insertBill(Bill bill) throws Exception {
        int status = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement("insert into Bill values(?,?,?,?,?,?,?)");
            ps.setInt(1, bill.getAccountID());
            ps.setLong(2, bill.getTotal());
            ps.setString(3, bill.getPayment());
            ps.setString(4, bill.getAddress());
            ps.setDate(5, bill.getDate());
            ps.setString(6, bill.getName());
            ps.setString(7, bill.getPhone());
            status = ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
        return status;
    }

    public int deleteBill(int billId) throws Exception {
        int status = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement("delete from Bill where bill_id = ?");
            ps.setInt(1, billId);
            status = ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
        return status;
    }

    public int deleteBillByAccountID(int accountID) throws Exception {
        int status = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement("delete from Bill where account_id = ?");
            ps.setInt(1, accountID);
            status = ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
        return status;
    }

    public int insertContact(Contact contact) throws Exception {
        int status = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement("insert into Contact values(?,?,?,?,?)");
            ps.setString(1, contact.getContactName());
            ps.setString(2, contact.getContactEmail());
            ps.setString(3, contact.getContactTitle());
            ps.setString(4, contact.getContactMessage());
            ps.setDate(5, contact.getContactDate());
            status = ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
        return status;
    }

    //sua 26-10
    public int editAccount(Account account) throws Exception {
        int status = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement("update Account set "
                    + "account_name=?,"
                    + "account_email=?,"
                    + "account_pass=?,"
                    + "account_phone=?"
                    + "isAdmin=0"
                    + "Status=0 where account_id = ?");
            ps.setString(1, account.getAccountName());
            ps.setString(2, account.getAccountEmail());
            ps.setString(3, account.getAccountPass());
            ps.setString(4, account.getAccountPhone());
            ps.setInt(5, account.getAccountID());
            status = ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
        return status;
    }

    public ArrayList<Account> getListAccount() throws Exception {
        try {
            String sql = "SELECT * FROM Account";
            con = getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<Account> list = new ArrayList<>();
            while (rs.next()) {
                Account account = new Account();
                account.setAccountID(rs.getInt(1));
                account.setAccountName(rs.getString(2));
                account.setAccountEmail(rs.getString(3));
                account.setAccountPass(rs.getString(4));
                account.setAccountPhone(rs.getString(5));
                account.setIsAdmin(rs.getInt(6));
                account.setStatus(rs.getInt(7));
                list.add(account);
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
    }

    public int updateCategory(int categoryId, String categoryName) throws Exception {
        int status = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement("update Category set category_name = N'" + categoryName + "' where category_id = ?");
            ps.setInt(1, categoryId);
            status = ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
        return status;
    }

    public int insertCategory(String categoryName) throws Exception {
        int status = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement("insert into Category values(?)");
            ps.setString(1, categoryName);
            status = ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
        return status;
    }

    public int deleteCategory(int categoryId) throws Exception {
        int status = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement("delete from Category where category_id = ?");
            ps.setInt(1, categoryId);
            status = ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
        return status;
    }

    public ArrayList<Books> getListAllBooks() throws Exception {
        try {
            String sql = "SELECT * FROM Books";
            con = getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<Books> list = new ArrayList<>();
            while (rs.next()) {
                Books books = new Books();
                books.setBookID(rs.getString("book_id"));
                books.setCategoryID(rs.getInt("category_id"));
                books.setBookName(rs.getString("book_name"));
                books.setBookImage(rs.getString("book_image"));
                books.setBookPrice(rs.getLong("book_price"));
                books.setBookDescription(rs.getString("book_description"));
                list.add(books);
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
    }

    public int editBook(Books book) throws Exception {
        int status = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement("update Books set "
                    + "category_id=?,"
                    + "book_name=?,"
                    + "book_image=?,"
                    + "book_price=?,"
                    + "book_description=?"
                    + " where book_id = ?");
            ps.setInt(1, book.getCategoryID());
            ps.setString(2, book.getBookName());
            ps.setString(3, book.getBookImage());
            ps.setLong(4, book.getBookPrice());
            ps.setString(5, book.getBookDescription());
            ps.setString(6, book.getBookID());
            status = ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
        return status;
    }

    public int insertBook(Books book) throws Exception {
        int status = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement("insert into Books values(?,?,?,?,?,?)");
            ps.setString(1, book.getBookID());
            ps.setInt(2, book.getCategoryID());
            ps.setString(3, book.getBookName());
            ps.setString(4, book.getBookImage());
            ps.setLong(5, book.getBookPrice());
            ps.setString(6, book.getBookDescription());
            status = ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
        return status;
    }

    public int deleteBook(String bookId) throws Exception {
        int status = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement("delete from Books where book_id = ?");
            ps.setString(1, bookId);
            status = ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
        return status;
    }

    public int deleteAccount(int accountId) throws Exception {
        int status = 0;
        deleteBillByAccountID(accountId);
        try {
            con = getConnection();
            ps = con.prepareStatement("delete from Account where account_id = ?");
            ps.setInt(1, accountId);
            status = ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
        return status;
    }

    public int updateAccount(int accountId) throws Exception {
        int status = 0;
        Account account = getAccountByID(accountId);
        try {
            con = getConnection();
            if (account.getStatus() == 0) {
                ps = con.prepareStatement("UPDATE [Account]\n"
                        + "SET [Status] = 1\n"
                        + "WHERE [account_id] = ?;");
            } else {
                ps = con.prepareStatement("UPDATE [Account]\n"
                        + "SET [Status] = 0\n"
                        + "WHERE [account_id] = ?;");
            }
            ps.setInt(1, accountId);
            status = ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
        return status;
    }

    public ArrayList<Bill> getListBill() throws Exception {
        try {
            String sql = "SELECT * FROM Bill";
            con = getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<Bill> list = new ArrayList<>();
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setBillID(rs.getInt(1));
                bill.setAccountID(rs.getInt(2));
                bill.setTotal(rs.getLong(3));
                bill.setPayment(rs.getString(4));
                bill.setAddress(rs.getString(5));
                bill.setDate(rs.getDate(6));
                bill.setName(rs.getString(7));
                bill.setPhone(rs.getString(8));
                list.add(bill);
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
    }

    public ArrayList<Contact> getListContacts() throws Exception {
        try {
            String sql = "SELECT * FROM Contact";
            con = getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<Contact> list = new ArrayList<>();
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setContactID(rs.getInt(1));
                contact.setContactName(rs.getString(2));
                contact.setContactEmail(rs.getString(3));
                contact.setContactTitle(rs.getString(4));
                contact.setContactMessage(rs.getString(5));
                contact.setContactDate(rs.getDate(6));
                list.add(contact);
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
    }

    public int deleteContact(int contactId) throws Exception {
        int status = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement("delete from Contact where contact_id = ?");
            ps.setInt(1, contactId);
            status = ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
        return status;
    }

    public Category getCategoryByName(String categoryName) throws Exception {
        Category category = null;
        String sql = "SELECT * FROM Category WHERE category_name=?";
        try {
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, categoryName);
            rs = ps.executeQuery();
            while (rs.next()) {
                int categoryID = rs.getInt(1);
                String name = rs.getString(2);
                category = new Category(categoryID, name);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
        return category;

    }

    public int deleteBillDetailByAccountId(int accountId) throws Exception {
        int status = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement("delete from BillDetails where account_id = ?");
            ps.setInt(1, accountId);
            status = ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(con);
        }
        return status;
    }
    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        List<Books> list = new ArrayList<>();
        list = dao.getBookByCateAndName(1, "a");
        for (Books books : list) {
            System.out.println(books);
        }
    }
}
