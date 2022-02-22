package ilobeyouboss.chapterThree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertTest {
    class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String message) {
            super(message);
        }

        private static final long serialVersionUID = 1L;
    }

    class Account {
        int balance;
        String name;

        Account(String name) {
            this.name = name;
        }

        void deposit(int dollars) {
            balance += dollars;
        }

        void withdraw(int dollars) {
            if (balance < dollars) {
                throw new InsufficientFundsException("balance only " + balance);
            }
            balance -= dollars;
        }

        public String getName() {
            return name;
        }

        public int getBalance() {
            return balance;
        }

        public boolean hasPositiveBalance() {
            return balance > 0;
        }
    }

    private Account account;

    @BeforeEach
    public void createAccount() {
        account = new Account("an account name");
    }

    @Test
    public void hasPositiveBalance() {
        account.deposit(50);
        assertTrue(account.hasPositiveBalance());
    }

    @Test
    public void depositIncreasesBalance() {
        int initialBalance = account.getBalance();
        account.deposit(100);
        assertTrue(account.getBalance() > initialBalance);
    }

    @Test
    public void 어설트댓_테스트하기() {
        account.deposit(100);
        assertThat(account.getBalance(), equalTo(100));
        assertThat(account.getBalance() > 0, is(true));
        assertThat(account.getName(), startsWith("a"));
        assertThat(new String[]{"a","b","c"}, equalTo(new String[]{"a","b","c"}));

        Account account = new Account("my big fat acct");
        assertThat(account.getName(), is(equalTo("my big fat acct")));
        assertThat(account.getName(), not(equalTo("testing~ ")));

        //null 값이나 null이 아닌 값을 검사하는 경우
        assertThat(account.getName(), is(not(nullValue())));
        assertThat(account.getName(), is(notNullValue()));
    }
}
