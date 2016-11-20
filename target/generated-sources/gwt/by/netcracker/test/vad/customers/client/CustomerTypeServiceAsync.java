package by.netcracker.test.vad.customers.client;

import by.netcracker.test.vad.customers.shared.CustomerType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CustomerTypeServiceAsync
{

    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see CustomerTypeService
     */
    void findOne( java.lang.Integer id, AsyncCallback<CustomerType> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see CustomerTypeService
     */
    void findAll( AsyncCallback<java.util.List<CustomerType>> callback );


    /**
     * Utility class to get the RPC Async interface from client-side code
     */
    public static final class Util 
    { 
        private static CustomerTypeServiceAsync instance;

        public static final CustomerTypeServiceAsync getInstance()
        {
            if ( instance == null )
            {
                instance = (CustomerTypeServiceAsync) GWT.create( CustomerTypeService.class );
            }
            return instance;
        }

        private Util()
        {
            // Utility class should not be instantiated
        }
    }
}
