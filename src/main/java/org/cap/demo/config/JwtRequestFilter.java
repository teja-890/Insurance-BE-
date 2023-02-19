package org.cap.demo.config;

import java.io.IOException;



import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cap.demo.service.CustomerDetailsService;
import org.cap.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



import io.jsonwebtoken.ExpiredJwtException;



@Component
public class JwtRequestFilter extends OncePerRequestFilter{
    
    @Autowired
    private JwtUtil jwtTokenUtil;
    
    @Autowired
    private CustomerDetailsService jwtUserDetailsService;



   @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        final String requestTokenHeader = request.getHeader("Authorization");
        
        String username=null;
        String jwtToken=null;
        
        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken= requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.extractUsername(jwtToken);
            }catch(IllegalArgumentException e) {
                System.out.println("Unable to get Jwt Token");
            }catch(ExpiredJwtException e) {
                System.out.println("Jwt Token has expired");
            }
        }else {
            logger.warn("Jwt Token does not started with Bearer ");
        }
        
        //After getting token , we are validating it
        if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
            
            //if token is valid , then configure Spring security to manually set authentication
            if(jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                //Implementation of AuthenticationDetailsSource which builds the details object from an HttpServletRequest object,
                //creating a WebAuthenticationDetails.
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                //After setting the authentication in the context, we specify
                //that the current user is authenticated
                //So it passes the Spring security configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        
        filterChain.doFilter(request, response);
        
    }



}
