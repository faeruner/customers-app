package by.netcracker.test.vad.customers.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CustomerTypesServiceAsync
{

    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see by.netcracker.test.vad.customers.client.CustomerTypesService
     */
    void findOne( java.lang.Integer id, AsyncCallback<by.netcracker.test.vad.customers.shared.CustomerTypes> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see by.netcracker.test.vad.customers.client.CustomerTypesService
     */
    void findAll( AsyncCallback<java.util.List<by.netcracker.test.vad.customers.shared.CustomerTypes>> callback );


    /**
     * Utility class to get the RPC Async interface from client-side code
     */
    public static final class Util 
    { 
        private static CustomerTypesServiceAsync instance;

        public static final CustomerTypesServiceAsync getInstance()
        {
            if ( instance == null )
            {
                instance = (CustomerTypesServiceAsync) GWT.create( CustomerTypesService.class );
            }
            return instance;
        }

        private Util()
        {
            // Utility class should not be instantiated
        }
    }
}
