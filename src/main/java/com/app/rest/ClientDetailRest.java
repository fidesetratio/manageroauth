package com.app.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.ClientDetails;
import com.app.service.ClientsService;

@RestController
@RequestMapping("/manager")
public class ClientDetailRest {

		@Autowired 
		private ClientsService clientsService;
		
		@RequestMapping(value="/listclients",method=RequestMethod.GET)
		public ResponseEntity<List<ClientDetails>> listclients() {
			List<ClientDetails> list = new ArrayList<ClientDetails>();
			list = clientsService.findAll();
			return new ResponseEntity<List<ClientDetails>>(list,HttpStatus.OK);
		}
		
		
		@RequestMapping(value="/saveclient",method=RequestMethod.POST)
		public ResponseEntity<String> saveClient(@RequestBody ClientDetails clientDetail){
			clientsService.add(clientDetail);
			return new ResponseEntity<String>("ok",HttpStatus.OK);
		}

		@RequestMapping(value="/deleteclient/{clientid}",method=RequestMethod.GET)
		public ResponseEntity<String> deleteclient(@PathVariable("clientid") String clientid){
			ClientDetails clientDetail = clientsService.findByClientId(clientid);
			clientsService.clientDeleteByClientId(clientDetail);
			return new ResponseEntity<String>("ok",HttpStatus.OK);
		}
		
		@RequestMapping(value="/viewclient/{clientid}",method=RequestMethod.GET)
		public ResponseEntity<ClientDetails> viewclient(@PathVariable("clientid") String clientid){
			ClientDetails clientDetail = clientsService.findByClientId(clientid);
			return new ResponseEntity<ClientDetails>(clientDetail,HttpStatus.OK);
		}
		
		@RequestMapping(value="/copyclient/{clientid}/{clientname}",method=RequestMethod.GET)
		public ResponseEntity<ClientDetails> copyclient(@PathVariable("clientid") String clientid,@PathVariable("clientname") String clientname){
			ClientDetails clientDetail = clientsService.findByClientId(clientid);
			ClientDetails cp = new ClientDetails();
			cp.setClientId(clientname);
			cp.setClientSecret(clientDetail.getClientSecret());
			cp.setAccessTokenValidity(clientDetail.getAccessTokenValidity());
			cp.setAdditionalInformation(clientDetail.getAdditionalInformation());
			cp.setAuthorities(clientDetail.getAuthorities());
			cp.setAuthorizationGrantTypes(clientDetail.getAuthorizationGrantTypes());
			cp.setAutoApprove(clientDetail.getAutoApprove());
			cp.setRefreshTokenValidity(clientDetail.getRefreshTokenValidity());
			cp.setResourceIds(clientDetail.getResourceIds());
			cp.setScope(clientDetail.getScope());
			clientsService.add(cp);
			return new ResponseEntity<ClientDetails>(clientDetail,HttpStatus.OK);
		}
		
}
