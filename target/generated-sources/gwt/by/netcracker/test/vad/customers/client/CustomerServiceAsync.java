package by.netcracker.test.vad.customers.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CustomerServiceAsync
{

    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see by.netcracker.test.vad.customers.client.CustomerService
     */
    void findOne( java.lang.Integer id, AsyncCallback<by.netcracker.test.vad.customers.shared.Customer> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see by.netcracker.test.vad.customers.client.CustomerService
     */
    void findPage( java.lang.Integer pageNumber, java.lang.Integer pageSize, AsyncCallback<java.util.List<by.netcracker.test.vad.customers.shared.Customer>> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see by.netcracker.test.vad.customers.client.CustomerService
     */
    void findAll( AsyncCallback<java.util.List<by.netcracker.test.vad.customers.shared.Customer>> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see by.netcracker.test.vad.customers.client.CustomerService
     */
    void delete( by.netcracker.test.vad.customers.shared.Customer entity, AsyncCallback<Void> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see by.netcracker.test.vad.customers.client.CustomerService
     */
    void save( by.netcracker.test.vad.customers.shared.Customer entity, AsyncCallback<by.netcracker.test.vad.customers.shared.Customer> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see by.netcracker.test.vad.customers.client.CustomerService
     */
    void findByFirstName( java.lang.String str, AsyncCallback<java.util.List<by.netcracker.test.vad.customers.shared.Customer>> callback );


    /**
     * Utility class to get the RPC Async interface from client-side code
     */
    public static final class Util 
    { 
        private static CustomerServiceAsync instance;

        public static final CustomerServiceAsync getInstance()
        {
            if ( instance == null )
            {
                instance = (CustomerServiceAsync) GWT.create( CustomerService.class );
            }
            return instance;
        }

        private Util()
        {
            // Utility class should not be instantiated
        }
    }
}
