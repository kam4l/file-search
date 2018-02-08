package exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class FileSearchExceptionMapper implements ExceptionMapper<FileSearchException>{

    @Override
    public Response toResponse(FileSearchException exception) {
        return Response.serverError().build();
    }
}
