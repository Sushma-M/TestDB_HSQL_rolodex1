/*Generated by WaveMaker Studio*/
package com.testdb_hsql.rolodex.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;

import com.testdb_hsql.rolodex.Address;
import com.testdb_hsql.rolodex.Customer;
import com.testdb_hsql.rolodex.Store;


/**
 * ServiceImpl object for domain model class Address.
 *
 * @see Address
 */
@Service("rolodex.AddressService")
public class AddressServiceImpl implements AddressService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressServiceImpl.class);

    @Autowired
	@Qualifier("rolodex.CustomerService")
	private CustomerService customerService;

    @Autowired
	@Qualifier("rolodex.StoreService")
	private StoreService storeService;

    @Autowired
    @Qualifier("rolodex.AddressDao")
    private WMGenericDao<Address, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Address, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "rolodexTransactionManager")
    @Override
	public Address create(Address addressInstance) {
        LOGGER.debug("Creating a new Address with information: {}", addressInstance);
        Address addressInstanceCreated = this.wmGenericDao.create(addressInstance);
        if(addressInstanceCreated.getCustomers() != null) {
            for(Customer customer : addressInstanceCreated.getCustomers()) {
                customer.setAddress(addressInstanceCreated);
                LOGGER.debug("Creating a new child Customer with information: {}", customer);
                customerService.create(customer);
            }
        }

        if(addressInstanceCreated.getStores() != null) {
            for(Store store : addressInstanceCreated.getStores()) {
                store.setAddress(addressInstanceCreated);
                LOGGER.debug("Creating a new child Store with information: {}", store);
                storeService.create(store);
            }
        }
        return addressInstanceCreated;
    }

	@Transactional(readOnly = true, value = "rolodexTransactionManager")
	@Override
	public Address getById(Integer addressIdInstance) throws EntityNotFoundException {
        LOGGER.debug("Finding Address by id: {}", addressIdInstance);
        Address addressInstance = this.wmGenericDao.findById(addressIdInstance);
        if (addressInstance == null){
            LOGGER.debug("No Address found with id: {}", addressIdInstance);
            throw new EntityNotFoundException(String.valueOf(addressIdInstance));
        }
        return addressInstance;
    }

    @Transactional(readOnly = true, value = "rolodexTransactionManager")
	@Override
	public Address findById(Integer addressIdInstance) {
        LOGGER.debug("Finding Address by id: {}", addressIdInstance);
        return this.wmGenericDao.findById(addressIdInstance);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "rolodexTransactionManager")
	@Override
	public Address update(Address addressInstance) throws EntityNotFoundException {
        LOGGER.debug("Updating Address with information: {}", addressInstance);
        this.wmGenericDao.update(addressInstance);

        Integer addressIdInstance = addressInstance.getAddressId();

        return this.wmGenericDao.findById(addressIdInstance);
    }

    @Transactional(value = "rolodexTransactionManager")
	@Override
	public Address delete(Integer addressIdInstance) throws EntityNotFoundException {
        LOGGER.debug("Deleting Address with id: {}", addressIdInstance);
        Address deleted = this.wmGenericDao.findById(addressIdInstance);
        if (deleted == null) {
            LOGGER.debug("No Address found with id: {}", addressIdInstance);
            throw new EntityNotFoundException(String.valueOf(addressIdInstance));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "rolodexTransactionManager")
	@Override
	public Page<Address> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Addresses");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "rolodexTransactionManager")
    @Override
    public Page<Address> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Addresses");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "rolodexTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service rolodex for table Address to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "rolodexTransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "rolodexTransactionManager")
    @Override
    public Page<Customer> findAssociatedCustomers(Integer addressId, Pageable pageable) {
        LOGGER.debug("Fetching all associated customers");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("address.addressId = '" + addressId + "'");

        return customerService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "rolodexTransactionManager")
    @Override
    public Page<Store> findAssociatedStores(Integer addressId, Pageable pageable) {
        LOGGER.debug("Fetching all associated stores");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("address.addressId = '" + addressId + "'");

        return storeService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service CustomerService instance
	 */
	protected void setCustomerService(CustomerService service) {
        this.customerService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service StoreService instance
	 */
	protected void setStoreService(StoreService service) {
        this.storeService = service;
    }

}

