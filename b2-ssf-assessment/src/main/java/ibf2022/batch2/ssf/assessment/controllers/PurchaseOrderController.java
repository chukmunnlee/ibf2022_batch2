package ibf2022.batch2.ssf.assessment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2022.batch2.ssf.assessment.models.Invoice;
import ibf2022.batch2.ssf.assessment.models.Item;
import ibf2022.batch2.ssf.assessment.models.Order;
import ibf2022.batch2.ssf.assessment.models.Quotation;
import ibf2022.batch2.ssf.assessment.models.ShippingAddress;
import ibf2022.batch2.ssf.assessment.services.PurchaseOrderService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping
public class PurchaseOrderController {

	public static final String ATTR_ORDER = "order";
	public static final String ATTR_ITEM = "item";
	public static final String ATTR_SHIPPING_ADDRESS = "shippingAddress";
	public static final String ATTR_ERROR = "error";
	public static final String ATTR_INVOICE = "invoice";

	@Autowired
	private PurchaseOrderService poSvc;

	@GetMapping(path={"/", "/index.html"})
	public String getIndex(Model model, HttpSession sess) {

		Order order = getOrder(sess);

		model.addAttribute(ATTR_ITEM, new Item());
		model.addAttribute(ATTR_ORDER, order);

		return "view1";
	}

	@PostMapping(path="/item")
	public String postItem(Model model, HttpSession sess,
			@ModelAttribute @Valid Item item, BindingResult bindings) {

		Order order = getOrder(sess);
		model.addAttribute(ATTR_ORDER, order);

		System.out.printf(">>> item: %s\n", item);

		if (bindings.hasErrors())
			return "view1";

		if (!poSvc.isInString(item)) {
			FieldError error = new FieldError(ATTR_ITEM, ATTR_ITEM, 
					"We do not stock %s".formatted(item.getItem()));
			bindings.addError(error);
			return "view1";
		}

		order.add(item);
		model.addAttribute(ATTR_ITEM, new Item());

		return "view1";
	}

	@GetMapping(path="/shippingaddress")
	public String getShippingAddress(Model model, HttpSession sess) {

		if (!hasOrder(sess)) {
			return "redirect:/";
		}

		model.addAttribute(ATTR_SHIPPING_ADDRESS, new ShippingAddress());

		return "view2";
	}

	@PostMapping(path="/checkout")
	public String postCheckout(Model model, HttpSession sess
			, @ModelAttribute @Valid ShippingAddress shippingAddress, BindingResult bindings) {

		System.out.printf(">>> shipping address: %s\n", shippingAddress);

		if (!hasOrder(sess)) {
			return "redirect:/";
		}

		if (bindings.hasErrors()) 
			return "view2";

		Order order = getOrder(sess);

		Quotation quotation;
		try {
			quotation = poSvc.getQuotations(order);
		} catch (Exception ex) {
			ex.printStackTrace();
			model.addAttribute(ATTR_ERROR, ex.getMessage());
			return "view2";
		}

		Invoice invoice = poSvc.createInvoice(shippingAddress, order, quotation);
		model.addAttribute(ATTR_INVOICE, invoice);

		sess.invalidate();

		return "view3";
	}

	private boolean hasOrder(HttpSession sess) {
		return null != sess.getAttribute(ATTR_ORDER);
	}

	private Order getOrder(HttpSession sess) {
		Order order = (Order)sess.getAttribute(ATTR_ORDER);
		if (null == order) {
			order = new Order();
			sess.setAttribute(ATTR_ORDER, order);
		}
		return order;
	}
}
