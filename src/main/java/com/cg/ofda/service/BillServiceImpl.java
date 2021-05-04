package com.cg.ofda.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ofda.entity.BillEntity;
import com.cg.ofda.exception.OFDAException;
import com.cg.ofda.model.BillModel;
import com.cg.ofda.repository.IBillRepository;
import com.cg.ofda.util.EMParserBill;

@Service
public class BillServiceImpl implements IBillService {

	@Autowired
	private IBillRepository billRepo;

	@Autowired
	private EMParserBill parser;

	public BillServiceImpl() {
		this.parser = new EMParserBill();
	}

	/*
	 * Parameterized for assigning
	 */

	public BillServiceImpl(IBillRepository billRepo) {
		super();
		this.billRepo = billRepo;
		this.parser = new EMParserBill();
	}

	/*
	 * Implementation of addBill method to add bill
	 */

	@Transactional
	@Override
	public BillModel addBill(BillModel bill) throws OFDAException {
		if (bill != null) {
			if (billRepo.existsById(bill.getBillId())) {
				throw new OFDAException("Bill with this id already exists");
			}
			bill = parser.parse(billRepo.save(parser.parse(bill)));
		}
		return bill;
	}

	/*
	 * Implementation of updateBill method to update existing bill
	 */

	@Transactional
	@Override
	public BillModel updateBill(BillModel bill) throws OFDAException {

		BillEntity bill1 = billRepo.findById(bill.getBillId()).orElse(null);
		if (bill1 == null) {
			throw new OFDAException("No Such Bill Found");
		}
		bill = parser.parse(billRepo.save(parser.parse(bill)));
		return bill;
	}

	/*
	 * Implementation of removeBill method to remove existing bill
	 */

	@Transactional
	@Override
	public boolean removeBill(Long billId) throws OFDAException {
		boolean isDeleted = false;
		BillEntity oldbill = billRepo.findById(billId).orElse(null);
		if (oldbill == null) {
			throw new OFDAException("no bill with id #" + billId + " present");
		} else {
			billRepo.deleteById(billId);
			isDeleted = true;
		}
		return isDeleted;

	}

	/*
	 * Implementation of viewBill method to view a bill
	 */

	@Override
	public BillModel viewBill(Long billId) throws OFDAException {
		BillEntity oldbill = billRepo.findById(billId).orElse(null);
		if (oldbill == null) {
			throw new OFDAException("no bill with id #" + billId + " present");
		}
		return parser.parse(billRepo.findById(billId).orElse(null));
	}

	/*
	 * Implementation of viewAllBills method to view all bills
	 */

	@Override
	public List<BillModel> viewAllBills() throws OFDAException {

		return billRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());

	}

}