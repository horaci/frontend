package controllers

import play.api.mvc._
import model.IdentityPage
import common.ExecutionContexts
import services.{IdentityUrlBuilder, IdRequestParser, ReturnUrlVerifier}
import com.google.inject.{Inject, Singleton}
import utils.SafeLogging


@Singleton
class EthicalAwardsController @Inject()(returnUrlVerifier: ReturnUrlVerifier,
                                        idRequestParser: IdRequestParser,
                                        idUrlBuilder: IdentityUrlBuilder,
                                        authAction: utils.AuthAction)
  extends Controller with ExecutionContexts with SafeLogging {

  val page = IdentityPage("/foo", "Foo", "foo")

  def ethicalAwardsForm(formId: String) = authAction.apply { implicit request =>
    val idRequest = idRequestParser(request)
    Ok(views.html.ethicalAwards.ethicalAwardsForm(page, formId, idRequest, idUrlBuilder))
  }
}