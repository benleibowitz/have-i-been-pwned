package org.seventwentyseventechnologies.haveibeenpwned.api.v3.transformer;

import lombok.NonNull;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Convert {@link Response} to {@link WebApplicationException}
 */
public class JAXRSExceptionProvider {
    /**
     * Return a web application exception for the response
     *
     * @param response response
     * @return exception
     */
    public WebApplicationException getException(@NonNull final Response response) {
        switch (response.getStatusInfo().toEnum()) {
            case BAD_REQUEST:
                return new BadRequestException(response);
            case UNAUTHORIZED:
                return new NotAuthorizedException(response);
            case FORBIDDEN:
                return new ForbiddenException(response);
            case NOT_FOUND:
                return new NotFoundException(response);
            case TOO_MANY_REQUESTS:
                return new ClientErrorException(response);
            case SERVICE_UNAVAILABLE:
                return new ServiceUnavailableException(response);
            default:
                if (response.hasEntity()) {
                    response.bufferEntity();
                }
                throw new ProcessingException("Unexpected response, status=" + response.getStatus() + ", message=" + response.readEntity(String.class));
        }
    }
}
